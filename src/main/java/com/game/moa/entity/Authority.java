package com.game.moa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_seq", nullable = false, unique = true, updatable = false)
    private Long authoritySeq;

    @Column(name = "name", nullable = false, length = 20, unique = true, updatable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "authority", cascade = CascadeType.PERSIST)
    private Set<MemberAuthority> memberAuthorities;

    protected Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
