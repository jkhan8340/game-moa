package com.game.moa.config.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataRedisTest
@Import(EmbeddedRedisServerConfig.class)
@ActiveProfiles("test")
class EmbeddedRedisServerConfigTest {
    @Test
    public void embeddedRedisStartTest() {
    }
}