package com.game.moa.vo;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@Jacksonized
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 7)
public class RefreshToken {

    @Id
    private String id;

    @Indexed
    private String accessToken;

    private String refreshToken;

}
