package com.game.moa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.moa.advice.GamemoaRestControllerAdvice;
import com.game.moa.auth.TokenProvider;
import com.game.moa.param.LoginParam;
import com.game.moa.vo.AuthorityVO;
import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LoginRestController.class)
class LoginRestControllerTest {

    @Autowired
    private LoginRestController loginRestController;

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private UserDetailsService userDetailsService;

    private MockMvc mockMvc;

    private static final ObjectMapper OBJECT_MAPPER;

    private static final String TOKEN = "token";
    private static final String TEST_ID = "gkswjdrl123";

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.loginRestController)
                .setControllerAdvice(new GamemoaRestControllerAdvice())
                .build();
    }

    @Test
    public void testAuthenticate() throws Exception {
        when(tokenProvider.createToken(any())).thenReturn(TOKEN);
        MemberVO memberVO = MemberVO.builder()
                .memberId(TEST_ID)
                .password(passwordEncoder.encode("1234"))
                .email("gkswjdrl123@naver.com")
                .authorities(Set.of(AuthorityVO.builder()
                                .authority("ROLE_USER")
                        .build()))
                .build();
        when(userDetailsService.loadUserByUsername(eq(TEST_ID))).thenReturn(memberVO);
        LoginParam loginParam = LoginParam.builder()
                .memberId("gkswjdrl123")
                .password("1234")
                .build();
        mockMvc.perform(post("/api/login/authenticate")
                        .header(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(loginParam)))
                .andDo(print())
                .andExpect(header().string("Authorization", TokenProvider.TOKEN_TYPE + "token"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {"message":"success","status_code":200,"data":"token"}
                    """))
                .andDo(print());
    }

    @TestConfiguration
    static class TestLoginControllerConfiguration {

        @Bean("bCryptPasswordEncoder")
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}