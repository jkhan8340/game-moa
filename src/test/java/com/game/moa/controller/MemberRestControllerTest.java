package com.game.moa.controller;

import com.game.moa.advice.GamemoaRestControllerAdvice;
import com.game.moa.auth.MemberVOArgumentResolver;
import com.game.moa.config.JacksonConfig;
import com.game.moa.exception.GamemoaException;
import com.game.moa.service.member.MemberService;
import com.game.moa.vo.AuthorityVO;
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

import java.util.List;
import java.util.Set;

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

    private static final MemberVO MOCK_MEMBER_VO;

    static {
        MOCK_MEMBER_VO = MemberVO.builder()
                .memberId("member")
                .name("한정기")
                .email("gkswjdrl123@naver.com")
                .authorities(Set.of(AuthorityVO.builder()
                                .authority("ROLE_USER")
                        .build()))
                .build();
    }

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
        when(MOCK_CUSTOM_ARGUMENT_RESOLVER.resolveArgument(any(), any(), any() ,any())).thenReturn(MOCK_MEMBER_VO);
        when(MOCK_CUSTOM_ARGUMENT_RESOLVER.supportsParameter(any())).thenReturn(true);
        when(memberService.findMemberByMemberId(eq("member"))).thenReturn(MOCK_MEMBER_VO);
        mockMvc.perform(get("/api/member"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {"message":"success","status_code":200,"data":{"member_id":"member","name":"한정기","email":"gkswjdrl123@naver.com","authorities":[{"authority":"ROLE_USER"}]}}
                """));
    }

    @Test
    public void testGetMemberList() throws Exception {
        when(memberService.getMemberList()).thenReturn(List.of(MOCK_MEMBER_VO));
        mockMvc.perform(get("/api/member/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {"message":"success","status_code":200,"data":[{"member_id":"member","name":"한정기","email":"gkswjdrl123@naver.com","authorities":[{"authority":"ROLE_USER"}]}]}
                """));
    }
}