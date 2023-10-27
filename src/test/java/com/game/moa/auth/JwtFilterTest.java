package com.game.moa.auth;

import com.game.moa.repository.redis.TokenRepository;
import com.game.moa.vo.RefreshToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import java.io.IOException;

import static com.game.moa.auth.TokenProvider.TOKEN_TYPE;
import static org.mockito.Mockito.*;

class JwtFilterTest {

    private static final JwtFilter JWT_FILTER;
    private static final TokenProvider TOKEN_PROVIDER;
    private static final TokenRepository TOKEN_REPOSITORY;

    private static final String TOKEN = "token";

    static {
        TOKEN_PROVIDER = mock(TokenProvider.class);
        TOKEN_REPOSITORY = mock(TokenRepository.class);
        JWT_FILTER = new JwtFilter(TOKEN_PROVIDER, TOKEN_REPOSITORY);
    }


    @Test
    public void testFilter() throws ServletException, IOException {
        Authentication authentication = mock(Authentication.class);
        when(TOKEN_PROVIDER.getAuthentication(eq(TOKEN))).thenReturn(authentication);
        when(TOKEN_REPOSITORY.findByAccessToken(eq(TOKEN))).thenReturn(RefreshToken.builder()
                        .accessToken(TOKEN)
                .build());

        FilterChain filterChain = mock(FilterChain.class);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getHeader(eq(TokenProvider.AUTHORIZATION_HEADER))).thenReturn(TOKEN_TYPE + TOKEN);
        when(httpServletRequest.getServletPath()).thenReturn("/test");

        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

        when(TOKEN_PROVIDER.validateToken(eq(TOKEN))).thenReturn(true);
        JWT_FILTER.doFilter(httpServletRequest, httpServletResponse, filterChain);

        verify(filterChain, times(1)).doFilter(eq(httpServletRequest), eq(httpServletResponse));
    }

}