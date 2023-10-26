package com.game.moa.service.member;


import com.game.moa.param.MemberParam;
import com.game.moa.vo.MemberVO;

import java.util.List;

public interface MemberService {

    MemberVO findMemberByMemberId(String memberId);

    MemberVO registerMember(MemberParam memberParam);

    List<MemberVO> getMemberList();
}
