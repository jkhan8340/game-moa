package com.game.moa.controller;


import com.game.moa.response.GamemoaResponse;
import com.game.moa.service.rune.RuneService;
import com.game.moa.vo.RuneVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rune")
public class RuneRestController {

    private final RuneService runeService;
    public RuneRestController(RuneService runeService) {
        this.runeService = runeService;
    }

    @GetMapping("/list")
    public ResponseEntity<GamemoaResponse<List<RuneVO>>> getRuneList() {
        return ResponseEntity.ok(GamemoaResponse.from(runeService.getRuneList(), "룬 목록 조회 성공",200));
    }

}
