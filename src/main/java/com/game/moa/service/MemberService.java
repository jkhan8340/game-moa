package com.game.moa.service;


import com.game.moa.param.MemberParam;
import com.game.moa.vo.MemberVO;

public interface MemberService {

    MemberVO findMemberByMemberId(String memberId);

    MemberVO registerMember(MemberParam memberParam);
}
