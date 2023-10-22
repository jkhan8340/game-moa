package com.game.moa.config.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(EmbeddedRedisServerConfig.class)
class EmbeddedRedisServerConfigTest {
    @Test
    public void embeddedRedisStartTest() {
    }
}