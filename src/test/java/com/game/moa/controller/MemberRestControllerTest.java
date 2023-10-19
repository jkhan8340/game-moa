package com.game.moa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.moa.advice.GamemoaRestControllerAdvice;
import com.game.moa.exception.GamemoaException;
import com.game.moa.param.MemberParam;
import com.game.moa.service.MemberService;
import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = MemberRestController.class
)
@ActiveProfiles("test")
class MemberRestControllerTest {

    @Autowired
    private MemberRestController memberRestController;

    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.memberRestController)
                .setControllerAdvice(new GamemoaRestControllerAdvice())
                .build();
    }

    @Test
    public void testGetNotFoundMember() throws Exception {
        when(memberService.findMemberByMemberId(eq("member1"))).thenThrow(new GamemoaException(HttpStatus.NOT_FOUND, "유저 못찾음"));
        mockMvc.perform(get("/api/member?member_id=member1"))
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
        when(memberService.findMemberByMemberId(eq("member"))).thenReturn(memberVO);
        mockMvc.perform(get("/api/member?member_id=member"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {"message":"success","status_code":200,"data":{"enabled":true,"username":"member","accountNonExpired":true,"credentialsNonExpired":true,"accountNonLocked":true,"member_id":"member","name":"한정기","email":"gkswjdrl123@naver.com"}}
                """));
    }

    @Test
    public void testValidationMember() throws Exception {
        MemberParam memberParam = MemberParam.builder()
                .build();
        mockMvc.perform(put("/api/member")
                        .header(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberParam)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                {"status_code":400,"data":{"password":"password는 필수입니다.","name":"name은 필수입니다.","email":"email은 필수입니다.","memberId":"member_id는 필수입니다."}}
                """));
    }
}