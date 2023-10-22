package com.game.moa.validation;

import com.game.moa.repository.jpa.MemberRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * ConstraintValidator 상속 구현 하면, spring bean 자동 주입.
 * <a href="http://dolszewski.com/spring/custom-validation-annotation-in-spring/"></a>
 */
public class IdDuplicationCheckerValidator implements ConstraintValidator<IdDuplicationChecker, String> {

    private final MemberRepository memberRepository;

    public IdDuplicationCheckerValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return memberRepository.findUserByMemberId(value) == null;
    }
}
