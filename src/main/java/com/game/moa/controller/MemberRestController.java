package com.game.moa.controller;

import com.game.moa.param.MemberParam;
import com.game.moa.response.GamemoaResponse;
import com.game.moa.service.MemberService;
import com.game.moa.vo.MemberVO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GamemoaResponse<MemberVO>> getMember(@RequestParam("member_id") String memberId) {
        return ResponseEntity
                .ok(GamemoaResponse.from(memberService.findMemberByMemberId(memberId), "success", 200));
    }

    @PutMapping
    public ResponseEntity<GamemoaResponse<MemberVO>> putMember(@Valid @RequestBody MemberParam memberParam) {
        return ResponseEntity.ok(GamemoaResponse.from(memberService.registerMember(memberParam), "회원가입에 성공하였습니다.", 200));
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GamemoaResponse<List<MemberVO>>> getMemberList() {
        return ResponseEntity.ok(GamemoaResponse.from(memberService.getMemberList(), "success", 200));
    }

}
