package com.game.moa.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberParam {

    @NotBlank(message = "member_id는 필수입니다.")
    @JsonProperty("member_id")
    private String memberId;

    @NotBlank(message = "name은 필수입니다.")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "email은 필수입니다.")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "password는 필수입니다.")
    @JsonProperty("password")
    private String password;

}
