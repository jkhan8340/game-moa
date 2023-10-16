package com.game.moa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.moa.advice.GamemoaRestControllerAdvice;
import com.game.moa.exception.GamemoaException;
import com.game.moa.service.MemberService;
import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberRestController.class)
class MemberRestControllerTest {

    @Autowired
    private MemberRestController memberRestController;

    private static final ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

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
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                    {"message":"유저 못찾음","code":404}
                    """))
                .andDo(print());
    }

    @Test
    public void testGetMember() throws Exception {
        MemberVO memberVO = MemberVO.builder()
                .memberId("member")
                .name("한정기")
                .email("gkswjdrl123@naver.com").build();

        String jsonString = OBJECT_MAPPER.writeValueAsString(memberVO);

        when(memberService.findMemberByMemberId(eq("member"))).thenReturn(memberVO);
        mockMvc.perform(get("/api/member?member_id=member"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonString))
                .andDo(print());
    }



}