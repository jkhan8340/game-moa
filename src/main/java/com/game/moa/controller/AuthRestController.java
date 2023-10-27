package com.game.moa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.moa.auth.TokenHelper;
import com.game.moa.auth.TokenProvider;
import com.game.moa.auth.TokenService;
import com.game.moa.param.LoginParam;
import com.game.moa.response.GamemoaResponse;
import com.game.moa.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
public class AuthRestController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    private final TokenService tokenService;

    public AuthRestController(AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider, TokenService tokenService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<GamemoaResponse<String>> login(@Valid @RequestBody LoginParam loginParam) throws JsonProcessingException {
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(loginParam.getMemberId(), loginParam.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        MemberVO memberVO = (MemberVO) authentication.getPrincipal();
        String token = tokenProvider.createToken(memberVO);
        String refreshToken = tokenProvider.createToken(memberVO, TokenProvider.UNLIMITED_EXPIRATION);
        tokenService.saveToken(memberVO.getMemberId(), token, refreshToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(TokenProvider.AUTHORIZATION_HEADER, TokenProvider.TOKEN_TYPE + token);
        return ResponseEntity.ok().headers(httpHeaders).body(GamemoaResponse.from(token, "success", 200));
    }

    @GetMapping("/logout")
    public ResponseEntity<GamemoaResponse<String>> logout(HttpServletRequest request) {
        tokenService.removeToken(TokenHelper.resolveToken(request));
        return ResponseEntity.ok().body(GamemoaResponse.from(null, "success", 200));
    }
}
