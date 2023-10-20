package com.game.moa.auth;

import com.game.moa.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Log4j2
@SpringBootTest
@ActiveProfiles("test")
class MemberVOArgumentResolverTest {

    @Autowired
    private MemberVOArgumentResolver memberVOArgumentResolver;
    private final static MethodParameter METHOD_PARAMETER;
    static {
        METHOD_PARAMETER = mock(MethodParameter.class);
    }

    @Test
    public void testArgumentResolve() {
        when(METHOD_PARAMETER.getParameterAnnotation(eq(MemberInfo.class))).thenReturn(mock(MemberInfo.class));
        Class<?> mockClass = MemberVO.class;
        when(METHOD_PARAMETER.getParameterType()).thenAnswer((invocation) -> mockClass);
        assertThat(memberVOArgumentResolver.supportsParameter(METHOD_PARAMETER)).isTrue();

        when(METHOD_PARAMETER.getParameterAnnotation(any())).thenReturn(null);
        assertThat(memberVOArgumentResolver.supportsParameter(METHOD_PARAMETER)).isFalse();
    }

    @Test
    public void test() {
        String memberId = "gkswjdrl123";
        MemberVO testMemberVO = MemberVO.builder()
                .memberId(memberId)
                .build();
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(testMemberVO);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        MemberVO memberVO = (MemberVO) memberVOArgumentResolver.resolveArgument(mock(), mock(), mock(), mock());
        assertThat(memberVO.getMemberId()).isEqualTo(memberId);
        log.info("find member : " + memberVO);
    }

    @TestConfiguration
    static class TestMemberVOArgumentResolverConfiguration {
        @Bean
        public MemberVOArgumentResolver memberVOArgumentResolver() {
            return new MemberVOArgumentResolver();
        }
    }

}