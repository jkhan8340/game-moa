package com.game.moa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.moa.auth.TokenProvider;
import com.game.moa.param.LoginParam;
import com.game.moa.response.GamemoaResponse;
import com.game.moa.vo.MemberVO;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginRestController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    public LoginRestController(AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<GamemoaResponse<String>> authenticate(@Valid @RequestBody LoginParam loginParam) throws JsonProcessingException {
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(loginParam.getMemberId(), loginParam.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        MemberVO memberVO = (MemberVO) authentication.getPrincipal();
        String token = tokenProvider.createToken(memberVO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(TokenProvider.AUTHORIZATION_HEADER, "Bearer " + token);
        return ResponseEntity.ok().headers(httpHeaders).body(GamemoaResponse.from(token, "success", 200));
    }

}
