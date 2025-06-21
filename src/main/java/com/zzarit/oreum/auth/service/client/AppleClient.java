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
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.security.PrivateKey;
import java.security.Security;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

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
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");

        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(APPLE_PRIVATE_KEY);
            PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(privateKeyBytes);
            return converter.getPrivateKey(privateKeyInfo);
        } catch (Exception e) {
            throw new InternalServerException("private key convert 과정에서 오류발생: " + e.getMessage());
        }
    }

    @Override
    public AuthProvider getProvider() {
        return AuthProvider.APPLE;
    }
}
