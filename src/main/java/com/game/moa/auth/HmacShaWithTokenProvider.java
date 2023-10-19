package com.game.moa.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.moa.util.Base64Utils;
import com.game.moa.vo.MemberVO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class HmacShaWithTokenProvider implements TokenProvider {

    private final long expirationMilliseconds;
    private static final String CLAIM_KEY = "MEMBER";

    private final Key key;

    private static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public HmacShaWithTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.token-expiration}") long expiration) {
        this.expirationMilliseconds = expiration * 1000;
        this.key = Keys.hmacShaKeyFor(Base64Utils.decodedString(secret).getBytes());
    }

    @Override
    public String createToken(MemberVO memberVO) throws JsonProcessingException {
        return Jwts.builder()
                .setSubject(memberVO.getMemberId())
                .claim(CLAIM_KEY, OBJECT_MAPPER.writeValueAsString(memberVO))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMilliseconds))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public Authentication getAuthentication(String token) throws JsonProcessingException {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        MemberVO memberVO = OBJECT_MAPPER.readValue(claims.get(CLAIM_KEY, String.class), MemberVO.class);
        return new UsernamePasswordAuthenticationToken(memberVO, "", memberVO.getAuthorities());
    }

    @Override
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
