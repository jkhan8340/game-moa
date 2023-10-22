package com.game.moa.vo;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class RefreshToken {

    @Id
    private String id;

}
