package com.game.moa.auth;

import com.game.moa.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MemberVOArgumentResolverTest {

    private final static MemberVOArgumentResolver MEMBER_VO_ARGUMENT_RESOLVER;
    private final static MethodParameter METHOD_PARAMETER;
    static {
        MEMBER_VO_ARGUMENT_RESOLVER = new MemberVOArgumentResolver();
        METHOD_PARAMETER = mock(MethodParameter.class);
    }

    @Test
    public void testArgumentResolve() {
        when(METHOD_PARAMETER.getParameterAnnotation(eq(MemberInfo.class))).thenReturn(mock(MemberInfo.class));
        Class<?> mockClass = MemberVO.class;
        when(METHOD_PARAMETER.getParameterType()).thenAnswer((invocation) -> mockClass);
        assertThat(MEMBER_VO_ARGUMENT_RESOLVER.supportsParameter(METHOD_PARAMETER)).isTrue();

        when(METHOD_PARAMETER.getParameterAnnotation(any())).thenReturn(null);
        assertThat(MEMBER_VO_ARGUMENT_RESOLVER.supportsParameter(METHOD_PARAMETER)).isFalse();
    }

}