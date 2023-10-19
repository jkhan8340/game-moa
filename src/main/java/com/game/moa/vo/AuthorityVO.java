package com.game.moa.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;
import org.springframework.security.core.GrantedAuthority;

@Builder
@Jacksonized
@EqualsAndHashCode
public class AuthorityVO implements GrantedAuthority {

    @JsonProperty("authority")
    private final String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
