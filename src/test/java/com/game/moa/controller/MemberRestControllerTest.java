package com.game.moa.controller;

import com.game.moa.advice.GamemoaRestControllerAdvice;
import com.game.moa.auth.MemberVOArgumentResolver;
import com.game.moa.config.JacksonConfig;
import com.game.moa.exception.GamemoaException;
import com.game.moa.service.MemberService;
import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberRestController.class)
@Import({JacksonConfig.class})
@ActiveProfiles("test")
class MemberRestControllerTest {

    @Autowired
    private MemberRestController memberRestController;

    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private static final MemberVOArgumentResolver MOCK_CUSTOM_ARGUMENT_RESOLVER = mock(MemberVOArgumentResolver.class);

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.memberRestController)
                .setCustomArgumentResolvers(MOCK_CUSTOM_ARGUMENT_RESOLVER)
                .setControllerAdvice(new GamemoaRestControllerAdvice())
                .build();
    }

    @Test
    public void testGetNotFoundMember() throws Exception {
        when(memberService.findMemberByMemberId(any())).thenThrow(new GamemoaException(HttpStatus.NOT_FOUND, "유저 못찾음"));
        mockMvc.perform(get("/api/member"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                    {"message":"유저 못찾음","status_code":404}
                    """))
                .andDo(print());
    }

    @Test
    public void testGetMember() throws Exception {

        MemberVO memberVO = MemberVO.builder()
                .memberId("member")
                .name("한정기")
                .email("gkswjdrl123@naver.com").build();
        when(MOCK_CUSTOM_ARGUMENT_RESOLVER.resolveArgument(any(), any(), any() ,any())).thenReturn(memberVO);
        when(MOCK_CUSTOM_ARGUMENT_RESOLVER.supportsParameter(any())).thenReturn(true);
        when(memberService.findMemberByMemberId(eq("member"))).thenReturn(memberVO);
        mockMvc.perform(get("/api/member"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {"message":"success","status_code":200,"data":{"member_id":"member","name":"한정기","email":"gkswjdrl123@naver.com"}}
                """));
    }
}