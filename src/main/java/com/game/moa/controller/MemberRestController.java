package com.game.moa.controller;

import com.game.moa.auth.MemberInfo;
import com.game.moa.param.MemberParam;
import com.game.moa.response.GamemoaResponse;
import com.game.moa.service.member.MemberService;
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
    public ResponseEntity<GamemoaResponse<MemberVO>> getMember(@MemberInfo MemberVO memberVO) {
        return ResponseEntity
                .ok(GamemoaResponse.from(memberService.findMemberByMemberId(memberVO.getMemberId()), "success", 200));
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
