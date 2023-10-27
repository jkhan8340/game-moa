package com.game.moa.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.moa.vo.MemberVO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TokenProvider {

    String AUTHORIZATION_HEADER = "Authorization";

    String TOKEN_TYPE = "Bearer ";

    long UNLIMITED_EXPIRATION = -1;

    String createToken(MemberVO memberVO) throws JsonProcessingException;

    String createToken(MemberVO memberVO, long tokenExpiration) throws JsonProcessingException;

    Authentication getAuthentication(String token) throws JsonProcessingException;

    boolean validateToken(String token);

}