package com.game.moa.repository.jpa;

import com.game.moa.config.JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(JpaConfig.class)
@ActiveProfiles("test")
class AuthorityRepositoryTest {

    @Test
    public void testAuthority() {

    }

}