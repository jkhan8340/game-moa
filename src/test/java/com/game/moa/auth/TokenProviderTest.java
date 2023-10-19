package com.game.moa.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    private static final String SECRET = "1234123412341234123412341234123412341234123412341234";
    private static final long EXPIRE_SECOND = 3;

    private static final String MEMBER_ID = "jkhan";

    private static final String TEST_ROLE = "TEST";

    @Test
    public void testToken() {
        String token = tokenProvider.createToken(MEMBER_ID, List.of(TEST_ROLE));
        Authentication authentication = tokenProvider.getAuthentication(token);
        assertThat(authentication.getName()).isEqualTo(MEMBER_ID);
        GrantedAuthority authority = new SimpleGrantedAuthority(TEST_ROLE);
        assertThat(authentication.getAuthorities().contains(authority)).isTrue();
    }

    @Test
    public void testTokenValidation() {
        String expiredToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqa2hhbiIsImF1dGgiOiJURVNUIiwiZXhwIjoxNjk3Njk0NDI3fQ.EUtKdrctFbfaiVild6dr59WZZ7VRy_PUPMxSDqjvYq0zN6OS7Z755DNKvcQMh2__A-OT2Ek4WouwitUu7o903Q";
        assertThat(tokenProvider.validateToken(expiredToken)).isFalse();
        String passToken = tokenProvider.createToken(MEMBER_ID, List.of(TEST_ROLE));
        assertThat(tokenProvider.validateToken(passToken)).isTrue();
        String invalidToken = "testtest";
        assertThat(tokenProvider.validateToken(invalidToken)).isFalse();
    }

    @TestConfiguration
    static class TestTokenProviderConfiguration {

        @Bean
        public TokenProvider tokenProvider() {
            return new HmacShaWithTokenProvider(SECRET, EXPIRE_SECOND);
        }

    }


}