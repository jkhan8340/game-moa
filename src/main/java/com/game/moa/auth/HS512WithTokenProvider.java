package com.game.moa.auth;

import com.game.moa.util.Base64Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class HS512WithTokenProvider implements TokenProvider {

    private final long expirationMilliseconds;
    private static final String AUTHORITIES_KEY = "auth";

    private final Key key;

    public HS512WithTokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.token-expiration}") long expiration) {
        this.expirationMilliseconds = expiration * 1000;
        this.key = Keys.hmacShaKeyFor(Base64Utils.decodedString(secret).getBytes());
    }

    @Override
    public String createToken(String memberId, List<String> role) {
        return Jwts.builder()
                .setSubject(memberId)
                .claim(AUTHORITIES_KEY, String.join(",", role))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMilliseconds))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String getMemberId(String token) {
        return null;
    }

    @Override
    public String getRole(String token) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
