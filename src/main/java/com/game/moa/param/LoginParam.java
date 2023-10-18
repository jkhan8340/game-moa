package com.game.moa.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginParam {

    @JsonProperty("member_id")
    private String memberId;

    @JsonProperty("password")
    private String password;

}
