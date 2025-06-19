package com.zzarit.oreum.logging.domain;

import jakarta.servlet.http.Cookie;
import lombok.Getter;

import java.util.Arrays;
import java.util.Base64;

@Getter
public class
MemberIdentifier {

    public static String ID_NOT_FOUND_INFO = "Not Found";

    private final String idInfo;

    public MemberIdentifier(String token) {
        this.idInfo = buildIdInfo(token);
    }

    private String buildIdInfo(String token) {
        if (token == null) {
            return ID_NOT_FOUND_INFO;
        }
        return getAccessTokenInfo(token);
    }

    private String getAccessTokenInfo(String jwtToken) {
        String[] tokenParts = jwtToken.split("\\.");
        if (tokenParts.length == 3) {
            String payload = new String(Base64.getUrlDecoder().decode(tokenParts[1]));
            return payload;
        }
        return ID_NOT_FOUND_INFO;
    }
}






