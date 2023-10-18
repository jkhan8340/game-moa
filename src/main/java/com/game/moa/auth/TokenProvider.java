package com.game.moa.auth;

import java.util.List;

public interface TokenProvider {

    String createToken(String memberId, List<String> role);

    String getMemberId(String token);

    String getRole(String token);

    boolean validateToken(String token);

}