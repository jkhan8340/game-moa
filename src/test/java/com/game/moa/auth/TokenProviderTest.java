package com.game.moa.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.moa.vo.AuthorityVO;
import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TokenProviderTest {

    private static final TokenProvider TOKEN_PROVIDER;

    private static final String SECRET = "1234123412341234123412341234123412341234123412341234";
    private static final long EXPIRE_SECOND = 3;

    private static final String MEMBER_ID = "jkhan";

    private static final AuthorityVO TEST_ROLE = AuthorityVO.builder().authority("TEST").build();

    static {
        TOKEN_PROVIDER = new HmacShaWithTokenProvider(SECRET, EXPIRE_SECOND);
    }
    @Test
    public void testToken() throws JsonProcessingException {
        MemberVO memberVO = MemberVO.builder().memberId(MEMBER_ID).authorities(Set.of(TEST_ROLE)).build();
        String token = TOKEN_PROVIDER.createToken(memberVO);
        Authentication authentication = TOKEN_PROVIDER.getAuthentication(token);
        assertThat(authentication.getName()).isEqualTo(MEMBER_ID);
        assertThat(authentication.getAuthorities().contains(TEST_ROLE)).isTrue();
    }

    @Test
    public void testTokenValidation() throws JsonProcessingException {
        String expiredToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqa2hhbiIsImF1dGgiOiJURVNUIiwiZXhwIjoxNjk3Njk0NDI3fQ.EUtKdrctFbfaiVild6dr59WZZ7VRy_PUPMxSDqjvYq0zN6OS7Z755DNKvcQMh2__A-OT2Ek4WouwitUu7o903Q";
        assertThat(TOKEN_PROVIDER.validateToken(expiredToken)).isFalse();
        MemberVO memberVO = MemberVO.builder().memberId(MEMBER_ID).authorities(Set.of(TEST_ROLE)).build();
        String passToken = TOKEN_PROVIDER.createToken(memberVO);
        assertThat(TOKEN_PROVIDER.validateToken(passToken)).isTrue();
        String invalidToken = "testtest";
        assertThat(TOKEN_PROVIDER.validateToken(invalidToken)).isFalse();
    }

}