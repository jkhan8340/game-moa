package com.game.moa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.moa.entity.Member;
import com.game.moa.param.MemberParam;
import com.game.moa.repository.MemberRepository;
import com.game.moa.service.MemberService;
import com.game.moa.vo.AuthorityVO;
import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Set;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MemberRestController.class)
class MemberRestControllerValidationTest {

    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }
    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    public void testValidationMember() throws Exception {
        MemberParam memberParam = MemberParam.builder()
                .build();
        mockMvc.perform(put("/api/member")
                        .header(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(memberParam)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                {"status_code":400,"data":{"password":"password는 필수입니다.","name":"name은 필수입니다.","email":"email은 필수입니다.","memberId":"member_id는 필수입니다."}}
                """));
    }

    @Test
    public void testDuplicationId() throws Exception {
        String duplicationId = "gkswjdrl123";
        Member mockMember = mock(Member.class);
        when(mockMember.getMemberId()).thenReturn(duplicationId);
        when(memberRepository.findUserByMemberId(eq(duplicationId))).thenReturn(mockMember);
        MemberParam memberParam = MemberParam.builder()
                .memberId(duplicationId)
                .name("한정기")
                .password("1234")
                .passwordConfirm("12345")
                .email("testtest")
                .build();
        mockMvc.perform(put("/api/member")
                        .header(CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(memberParam)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                {"message":"success","status_code":400,"data":{"password":"비밀번호가 일치하지 않습니다.","email":"must be a well-formed email address","memberId":"이미 존재하는 ID 입니다."}}
                """));
    }

    @Test
    public void testPutMember() throws Exception {
        String memberId = "member";
        MemberVO memberVO = MemberVO.builder()
                .memberId(memberId)
                .name("한정기")
                .email("gkswjdrl123@naver.com")
                .authorities(Set.of(AuthorityVO
                        .builder()
                                .authority("ROLE_USER")
                        .build())).build();
        MemberParam memberParam = MemberParam.builder()
                .memberId(memberId)
                .name("한정기")
                .email("gkswjdrl123@naver.com")
                .passwordConfirm("1234")
                .password("1234")
                .build();
        when(memberService.registerMember(argThat((param) -> param.getMemberId().equals(memberId)))).thenReturn(memberVO);
        mockMvc.perform(put("/api/member")
                        .content(OBJECT_MAPPER.writeValueAsString(memberParam))
                        .header(CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {"message":"회원가입에 성공하였습니다.","status_code":200,"data":{"member_id":"member","name":"한정기","email":"gkswjdrl123@naver.com","authorities":[{"authority":"ROLE_USER"}]}}
                """));
    }
}