package com.game.moa.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TokenHelperTest {

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTc4NDEwMDIzN30.mm-2L-vEQq4VyKbasc0amTC0S-PMb_2Jz9LZ7Thvtr7X30tbDiOLi7-WfZ1lCXuT1iEktZcF5qUzTC3lCgx-ew";

    @Test
    public void testTokenResolve() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(TokenProvider.AUTHORIZATION_HEADER)).thenReturn("Bearer " + TOKEN);
        String resultToken = TokenHelper.resolveToken(request);
        assertThat(resultToken).isEqualTo(TOKEN);

        when(request.getHeader(TokenProvider.AUTHORIZATION_HEADER)).thenReturn(TOKEN);
        String resultToken1 = TokenHelper.resolveToken(request);
        assertThat(resultToken1).isNull();
    }

}