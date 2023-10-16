package com.game.moa.service;

import com.game.moa.exception.GamemoaException;
import com.game.moa.repository.MemberRepository;
import com.game.moa.vo.MemberVO;
import com.game.moa.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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
}
