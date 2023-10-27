package com.game.moa.auth;

import com.game.moa.repository.redis.TokenRepository;
import com.game.moa.vo.RefreshToken;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void saveToken(String id, String accessToken, String refreshToken) {
        tokenRepository.save(RefreshToken.builder()
                .id(id)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build());
    }

    @Override
    public void removeToken(String accessToken) {
        tokenRepository.delete(tokenRepository.findByAccessToken(accessToken));
    }
}
