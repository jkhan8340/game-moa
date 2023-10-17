package com.game.moa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seq", nullable = false, unique = true, updatable = false)
    private Long seq;

    @Column(name = "name", nullable = false, length = 20, unique = true, updatable = false)
    private String name;

    protected Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }

}
