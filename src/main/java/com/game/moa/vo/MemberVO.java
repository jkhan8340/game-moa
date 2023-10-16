package com.game.moa.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class MemberVO {

    @JsonProperty("member_id")
    private final String memberId;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("email")
    private final String email;

}
