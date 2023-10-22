package com.game.moa.service;

import com.game.moa.entity.Member;
import com.game.moa.repository.jpa.MemberRepository;
import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginServiceImplTest {

    private static final LoginService LOGIN_SERVICE;

    private static final MemberRepository MEMBER_REPOSITORY;

    static {
        MEMBER_REPOSITORY = mock(MemberRepository.class);
        LOGIN_SERVICE = new LoginServiceImpl(MEMBER_REPOSITORY);
    }

    private final static String NAME = "한정기";
    private final static String MEMBER_ID = "test";
    private final static String EMAIL = "gkswjdrl123@naver.com";

    private static final Member MOCK_MEMBER;
    static {
        MOCK_MEMBER = mock(Member.class);
        when(MOCK_MEMBER.getMemberId()).thenReturn(MEMBER_ID);
        when(MOCK_MEMBER.getName()).thenReturn(NAME);
        when(MOCK_MEMBER.getEmail()).thenReturn(EMAIL);
    }

    @Test
    public void testLoadUserByUsername() {
        when(MEMBER_REPOSITORY.findUserByMemberId(eq(MEMBER_ID))).thenReturn(MOCK_MEMBER);
        UserDetails userDetails = LOGIN_SERVICE.loadUserByUsername(MEMBER_ID);
        assertThat(userDetails.getUsername()).isEqualTo(MEMBER_ID);
        MemberVO memberVO = (MemberVO) userDetails;
        assertThat(memberVO.getMemberId()).isEqualTo(MEMBER_ID);
        assertThat(memberVO.getName()).isEqualTo(NAME);
        assertThat(memberVO.getEmail()).isEqualTo(EMAIL);
    }
}