package com.game.moa.auth;

import org.springframework.security.core.Authentication;

import java.util.List;

public interface TokenProvider {

    String AUTHORIZATION_HEADER = "Authorization";

    String createToken(String memberId, List<String> role);

    Authentication getAuthentication(String token);

    boolean validateToken(String token);

}