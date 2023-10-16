package com.game.moa.controller;

import com.game.moa.service.MemberService;
import com.game.moa.vo.MemberVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<MemberVO> getMember(@RequestParam("member_id") String memberId) {
        return ResponseEntity
                .ok(this.memberService.findMemberByMemberId(memberId));
    }

}
