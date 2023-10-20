package com.game.moa.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class TokenHelper {

    protected static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TokenProvider.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
