package com.game.moa.auth;

public interface TokenService {

    void saveToken(String id, String accessToken, String refreshToken);

    void removeToken(String accessToken);

}
