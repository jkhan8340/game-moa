package com.game.moa.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class TokenHelper {

    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TokenProvider.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TokenProvider.TOKEN_TYPE)) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
