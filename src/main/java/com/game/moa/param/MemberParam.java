package com.game.moa.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.game.moa.validation.IdDuplicationChecker;
import com.game.moa.validation.PasswordConfirmChecker;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@PasswordConfirmChecker
public class MemberParam {

    @IdDuplicationChecker
    @NotBlank(message = "member_id는 필수입니다.")
    @JsonProperty("member_id")
    private String memberId;

    @NotBlank(message = "name은 필수입니다.")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "email은 필수입니다.")
    @Email
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "password는 필수입니다.")
    @JsonProperty("password")
    private String password;

    @JsonProperty("passwordConfirm")
    private String passwordConfirm;

}
