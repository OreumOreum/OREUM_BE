package com.zzarit.oreum.auth.service.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zzarit.oreum.auth.service.dto.AppleSocialTokenInfoResponseDto;
import com.zzarit.oreum.global.exception.InternalServerException;
import com.zzarit.oreum.member.domain.repository.AuthProvider;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Security;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

/**
 * 애플 OAuth 인증 클라이언트
 * 
 * Apple Sign In 인증 코드를 사용하여 사용자 인증을 처리합니다.
 * Client Secret을 자체 생성하여 Apple ID Token API를 호출하고,
 * 반환된 ID 토큰을 디코딩하여 사용자 정보를 추출합니다.
 */
@RequiredArgsConstructor
@Component
public class AppleClient implements OAuthClient {
    @Value("${APPLE_TEAM_ID}")
    private String APPLE_TEAM_ID;

    @Value("${APPLE_LOGIN_KEY}")
    private String APPLE_LOGIN_KEY;

    @Value("${APPLE_CLIENT_ID}")
    private String APPLE_CLIENT_ID;

    @Value("${APPLE_PRIVATE_KEY}")
    private String APPLE_PRIVATE_KEY;

    private final String APPLE_AUTH_URL = "https://appleid.apple.com";

    private final RestClient restClient;


    /**
     * Apple ID_TOKEN API 호출 -> ID 토큰을 Decoding -> 사용자 고유 아이디 반환
     * @param authorizationCode 사용자로부터 받은 인증 코드
     * @return 사용자 고유 아이디 반환
     */
    @Override
    public String getUserInfo(String authorizationCode) {
        String requestBody = "client_id=" + APPLE_CLIENT_ID +
                "&client_secret=" + generateClientSecret() +
                "&grant_type=" + "authorization_code" +
                "&code=" + authorizationCode;

        AppleSocialTokenInfoResponseDto tokenResponse = restClient.post()
                .uri(APPLE_AUTH_URL + "/auth/token")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    String errorBody = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
                    System.err.println("Apple OAuth Error Response: " + errorBody);
                    throw new InternalServerException("APPLE ID_TOKEN 요청 중 에러 발생 하였습니다.");
                })
                .body(AppleSocialTokenInfoResponseDto.class);


        DecodedJWT decodedJWT = JWT.decode(tokenResponse.getIdToken());

        return AuthProvider.APPLE.buildLoginId(decodedJWT.getClaim("sub").asString());
    }

    /**
     * ID_TOKEN 요청 하기 위한 자체 토큰 발급
     * @return ClientSecret
     */
    private String generateClientSecret() {
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(5);

        return Jwts.builder()
                .setHeaderParam(JwsHeader.KEY_ID, APPLE_LOGIN_KEY)
                .setIssuer(APPLE_TEAM_ID)
                .setAudience(APPLE_AUTH_URL)
                .setSubject(APPLE_CLIENT_ID)
                .setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
                .setIssuedAt(new Date())
                .signWith(getPrivateKey(), SignatureAlgorithm.ES256)
                .compact();
    }

    /**
     * 자체 토큰 서명할 privateKey 리턴
     * Apple에서 발급한 .p8 파일 base64로 인코딩한 값
     * @return PrivateKey
     */
    private PrivateKey getPrivateKey() {
        try (PEMParser parser = new PEMParser(new StringReader(APPLE_PRIVATE_KEY.replace("\\n", "\n")))) {
            Object obj = parser.readObject();
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter(); // 굳이 Provider 지정 안 해도 됨

            if (obj instanceof PrivateKeyInfo pkInfo) {
                return converter.getPrivateKey(pkInfo);
            } else if (obj instanceof PEMKeyPair kp) { // 혹시 BEGIN EC PRIVATE KEY 형태일 때
                return converter.getKeyPair(kp).getPrivate();
            } else {
                throw new IllegalArgumentException("지원하지 않는 PEM 형식: " + (obj == null ? "null" : obj.getClass()));
            }
        } catch (Exception e) {
            throw new InternalServerException("private key convert 오류: " + e.getMessage());
        }
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.APPLE;
    }
}
