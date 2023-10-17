package com.game.moa.service;

import com.game.moa.entity.Member;
import com.game.moa.exception.GamemoaException;
import com.game.moa.repository.MemberRepository;
import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(value = "classpath:application-test.yml")
class MemberServiceImplTest {

    @MockBean
    private MemberRepository repository;

    @Autowired
    private MemberService memberService;

    private static final Member MOCK_MEMBER;
    static {
        MOCK_MEMBER = mock(Member.class);
        when(MOCK_MEMBER.getMemberId()).thenReturn("test");
        when(MOCK_MEMBER.getName()).thenReturn("한정기");
        when(MOCK_MEMBER.getEmail()).thenReturn("gkswjdrl123@naver.com");
    }

    @Test
    public void testMemberFind() {
        String memberId = "test";
        when(repository.findUserByMemberId(eq(memberId))).thenReturn(MOCK_MEMBER);
        MemberVO memberVO = memberService.findMemberByMemberId(memberId);

        assertThat(memberVO.getMemberId()).isEqualTo(memberId);
        assertThat(memberVO.getName()).isEqualTo("한정기");
        assertThat(memberVO.getEmail()).isEqualTo("gkswjdrl123@naver.com");
    }

    @Test
    public void testNotFoundMember() {
        when(repository.findUserByMemberId(any())).thenReturn(null);
        assertThatThrownBy(() -> memberService.findMemberByMemberId("test"))
                .isInstanceOf(GamemoaException.class)
                .hasMessage("존재하지 않는 회원입니다.");
    }

    @Test
    public void testPasswordEncoder() {
        String password = "1234";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodingPassword = passwordEncoder.encode(password);
        assertThat(passwordEncoder.matches(password, encodingPassword)).isTrue();
    }
}