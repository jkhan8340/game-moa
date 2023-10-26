package com.game.moa.vo;

import com.game.moa.entity.RuneOption;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RuneOptionVO {

    private final String ability;
    private final RuneOption.ItemType itemType;

}
