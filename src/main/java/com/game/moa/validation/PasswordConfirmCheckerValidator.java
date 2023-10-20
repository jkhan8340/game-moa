package com.game.moa.validation;

import com.game.moa.param.MemberParam;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.thymeleaf.util.StringUtils;

public class PasswordConfirmCheckerValidator implements ConstraintValidator<PasswordConfirmChecker, MemberParam> {

    @Override
    public boolean isValid(MemberParam value, ConstraintValidatorContext context) {
        if(!StringUtils.isEmpty(value.getPassword())
                && !value.getPassword().equals(value.getPasswordConfirm())) {

            ConstraintDescriptor<?> descriptor = ((ConstraintValidatorContextImpl) context).getConstraintDescriptor();
            String message = (String) descriptor.getAttributes().get("message");

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
