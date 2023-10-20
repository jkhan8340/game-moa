package com.game.moa.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdDuplicationCheckerValidator.class)
public @interface IdDuplicationChecker {

    String message() default "이미 존재하는 ID 입니다.";

    Class[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
