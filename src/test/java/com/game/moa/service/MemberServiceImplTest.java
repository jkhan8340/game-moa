package com.game.moa.service;

import com.game.moa.entity.Authority;
import com.game.moa.entity.Member;
import com.game.moa.entity.MemberAuthority;
import com.game.moa.exception.GamemoaException;
import com.game.moa.param.MemberParam;
import com.game.moa.repository.jpa.AuthorityRepository;
import com.game.moa.repository.jpa.MemberRepository;
import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MemberServiceImplTest {

    private static final AuthorityRepository AUTHORITY_REPOSITORY;

    private static final MemberRepository REPOSITORY;

    private static final PasswordEncoder PASSWORD_ENCODER;

    private static final MemberService MEMBER_SERVICE;

    static {
        AUTHORITY_REPOSITORY = mock(AuthorityRepository.class);
        REPOSITORY = mock(MemberRepository.class);
        PASSWORD_ENCODER = new BCryptPasswordEncoder();
        MEMBER_SERVICE = new MemberServiceImpl(REPOSITORY,  PASSWORD_ENCODER, AUTHORITY_REPOSITORY);
    }

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
        MemberAuthority memberAuthority = new MemberAuthority();
        memberAuthority.setAuthority(new Authority("ROLE_USER"));
        Set<MemberAuthority> memberAuthorities = Set.of(memberAuthority);
        when(MOCK_MEMBER.getMemberAuthorities()).thenReturn(memberAuthorities);
    }

    @Test
    public void testMemberFind() {
        when(REPOSITORY.findUserByMemberId(eq(MEMBER_ID))).thenReturn(MOCK_MEMBER);
        MemberVO memberVO = MEMBER_SERVICE.findMemberByMemberId(MEMBER_ID);

        assertThat(memberVO.getMemberId()).isEqualTo(MEMBER_ID);
        assertThat(memberVO.getName()).isEqualTo(NAME);
        assertThat(memberVO.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    public void testNotFoundMember() {
        when(REPOSITORY.findUserByMemberId(any())).thenReturn(null);
        assertThatThrownBy(() -> MEMBER_SERVICE.findMemberByMemberId("test"))
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
        when(REPOSITORY
                .save(argThat((arg) -> arg.getMemberId().equals(MOCK_MEMBER.getMemberId()) && PASSWORD_ENCODER.matches(PASSWORD, arg.getPassword()))))
                .thenReturn(MOCK_MEMBER);
        when(AUTHORITY_REPOSITORY.findByName(any())).thenReturn(new Authority("ROLE_USER"));
        MemberVO memberVO = MEMBER_SERVICE.registerMember(memberParam);
        assertThat(memberVO.getMemberId()).isEqualTo(MEMBER_ID);
        assertThat(memberVO.getEmail()).isEqualTo(EMAIL);
        assertThat(memberVO.getName()).isEqualTo(NAME);
    }

    @Test
    public void testGetMemberList() {
        when(REPOSITORY.findAllMemberForAuthorities()).thenReturn(List.of(MOCK_MEMBER));
        List<MemberVO> memberVOList = MEMBER_SERVICE.getMemberList();
        assertThat(memberVOList.size()).isEqualTo(1);
        assertThat(memberVOList.get(0).getMemberId()).isEqualTo(MEMBER_ID);
        assertThat(memberVOList.get(0).getEmail()).isEqualTo(EMAIL);
        assertThat(memberVOList.get(0).getName()).isEqualTo(NAME);
        assertThat(memberVOList.get(0).getAuthorities().size()).isEqualTo(1);
        assertThat(memberVOList.get(0).getAuthorities().iterator().next().getAuthority()).isEqualTo("ROLE_USER");
    }

}