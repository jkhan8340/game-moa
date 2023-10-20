package com.game.moa.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class LoginParam {

    @JsonProperty("member_id")
    private String memberId;

    @JsonProperty("password")
    private String password;

}
