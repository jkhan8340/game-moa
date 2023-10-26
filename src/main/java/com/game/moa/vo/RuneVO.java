package com.game.moa.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class RuneVO {

    @JsonProperty("number")
    private final int number;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("english_name")
    private final String englishName;

    @JsonProperty("level")
    private final int level;

    @JsonProperty("rune_options")
    private final Set<RuneOptionVO> runeOptions;

}
