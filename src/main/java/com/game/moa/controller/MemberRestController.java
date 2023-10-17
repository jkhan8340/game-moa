package com.game.moa.controller;

import com.game.moa.param.MemberParam;
import com.game.moa.service.MemberService;
import com.game.moa.vo.MemberVO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<String> putMember(@Valid @RequestBody MemberParam memberParam) {
        memberService.registerMember(memberParam);
        return ResponseEntity.ok("success");
    }

}
