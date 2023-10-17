package com.game.moa.service;

import com.game.moa.exception.GamemoaException;
import com.game.moa.param.MemberParam;
import com.game.moa.repository.MemberRepository;
import com.game.moa.vo.MemberVO;
import com.game.moa.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
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
    public void registerMember(MemberParam memberParam) {
        String encodingPassword = passwordEncoder.encode(memberParam.getPassword());
        Member member = new Member(memberParam.getMemberId(), memberParam.getName(), memberParam.getEmail(), encodingPassword);
        Member registerMember = memberRepository.save(member);
    }
}
