package com.game.moa.service;

import com.game.moa.entity.Member;
import com.game.moa.entity.MemberAuthority;
import com.game.moa.exception.GamemoaException;
import com.game.moa.param.MemberParam;
import com.game.moa.repository.AuthorityRepository;
import com.game.moa.repository.MemberRepository;
import com.game.moa.vo.MemberVO;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    private MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public MemberVO findMemberByMemberId(String memberId) {
        Member member = memberRepository.findUserByMemberId(memberId);
        if (member == null) {
            throw new GamemoaException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");
        }
        return MemberVO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    @Override
    public MemberVO registerMember(MemberParam memberParam) {
        String encodingPassword = passwordEncoder.encode(memberParam.getPassword());
        MemberAuthority memberAuthority = new MemberAuthority();
        memberAuthority.setAuthority(authorityRepository.findAuthorityByName("ROLE_USER"));
        Set<MemberAuthority> memberAuthorities = new HashSet<>();
        memberAuthorities.add(memberAuthority);
        Member member = new Member(memberParam.getMemberId(),
                memberParam.getName(),
                memberParam.getEmail(),
                encodingPassword, memberAuthorities, LocalDateTime.now(), null);
        memberAuthority.setMember(member);
        Member registerMember = memberRepository.save(member);
        return MemberVO.builder()
                .memberId(registerMember.getMemberId())
                .name(registerMember.getName())
                .email(registerMember.getEmail())
                .authorities(registerMember.getMemberAuthorities().stream().map(MemberAuthority::getAuthority).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public List<MemberVO> getMemberList() {
        List<Member> memberList = memberRepository.findAllMemberForAuthorities();
        return memberList.stream().map(member -> MemberVO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .authorities(member.getMemberAuthorities().stream().map(MemberAuthority::getAuthority).collect(Collectors.toSet()))
                .build()).toList();
    }
}
