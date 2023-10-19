package com.game.moa.service;

import com.game.moa.entity.Member;
import com.game.moa.exception.GamemoaException;
import com.game.moa.param.MemberParam;
import com.game.moa.repository.MemberRepository;
import com.game.moa.util.Base64Utils;
import com.game.moa.vo.MemberVO;
import org.apache.logging.log4j.util.Base64Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.WeakHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
@ActiveProfiles("test")
class MemberServiceImplTest {

    @MockBean
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    private final static String NAME = "한정기";
    private final static String MEMBER_ID = "test";
    private final static String EMAIL = "gkswjdrl123@naver.com";
    private final static String PASSWORD = "1234";
    private final static long MEMBER_SEQ = 1L;

    private static final Member MOCK_MEMBER;
    static {
        MOCK_MEMBER = mock(Member.class);
        when(MOCK_MEMBER.getMemberId()).thenReturn(MEMBER_ID);
        when(MOCK_MEMBER.getName()).thenReturn(NAME);
        when(MOCK_MEMBER.getEmail()).thenReturn(EMAIL);
        when(MOCK_MEMBER.getMemberSeq()).thenReturn(MEMBER_SEQ);
        when(MOCK_MEMBER.getPassword()).thenReturn(PASSWORD);
    }

    @Test
    public void testMemberFind() {
        when(repository.findUserByMemberId(eq(MEMBER_ID))).thenReturn(MOCK_MEMBER);
        MemberVO memberVO = memberService.findMemberByMemberId(MEMBER_ID);

        assertThat(memberVO.getMemberId()).isEqualTo(MEMBER_ID);
        assertThat(memberVO.getName()).isEqualTo(NAME);
        assertThat(memberVO.getEmail()).isEqualTo(EMAIL);
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

    @Test
    public void testRegisterMember() {
        MemberParam memberParam = MemberParam.builder()
                .memberId(MEMBER_ID)
                .password(PASSWORD)
                .email(EMAIL)
                .name(NAME)
                .build();
        when(repository
                .save(argThat((arg) -> arg.getMemberId().equals(MOCK_MEMBER.getMemberId()) && passwordEncoder.matches(PASSWORD, arg.getPassword()))))
                .thenReturn(MOCK_MEMBER);
        MemberVO memberVO = memberService.registerMember(memberParam);
        System.out.println(memberVO.getPassword());
        assertThat(memberVO.getMemberId()).isEqualTo(MEMBER_ID);
        assertThat(memberVO.getEmail()).isEqualTo(EMAIL);
        assertThat(memberVO.getName()).isEqualTo(NAME);
        assertThat(memberVO.getPassword()).isEqualTo(PASSWORD);
    }
}