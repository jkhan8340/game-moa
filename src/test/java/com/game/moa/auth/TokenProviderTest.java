package com.game.moa.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.moa.entity.Authority;
import com.game.moa.vo.AuthorityVO;
import com.game.moa.vo.MemberVO;
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
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    private static final String SECRET = "1234123412341234123412341234123412341234123412341234";
    private static final long EXPIRE_SECOND = 3;

    private static final String MEMBER_ID = "jkhan";

    private static final AuthorityVO TEST_ROLE = AuthorityVO.builder().authority("TEST").build();

    @Test
    public void testToken() throws JsonProcessingException {
        MemberVO memberVO = MemberVO.builder().memberId(MEMBER_ID).authorities(Set.of(TEST_ROLE)).build();
        String token = tokenProvider.createToken(memberVO);
        Authentication authentication = tokenProvider.getAuthentication(token);
        assertThat(authentication.getName()).isEqualTo(MEMBER_ID);
        assertThat(authentication.getAuthorities().contains(TEST_ROLE)).isTrue();
    }

    @Test
    public void testTokenValidation() throws JsonProcessingException {
        String expiredToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqa2hhbiIsImF1dGgiOiJURVNUIiwiZXhwIjoxNjk3Njk0NDI3fQ.EUtKdrctFbfaiVild6dr59WZZ7VRy_PUPMxSDqjvYq0zN6OS7Z755DNKvcQMh2__A-OT2Ek4WouwitUu7o903Q";
        assertThat(tokenProvider.validateToken(expiredToken)).isFalse();
        MemberVO memberVO = MemberVO.builder().memberId(MEMBER_ID).authorities(Set.of(TEST_ROLE)).build();
        String passToken = tokenProvider.createToken(memberVO);
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