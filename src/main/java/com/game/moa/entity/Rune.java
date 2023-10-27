package com.game.moa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rune")
@Getter
@Setter
public class Rune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rune_seq", nullable = false, updatable = false, unique = true)
    private Long runeSeq;

    @Column(name = "number")
    private Integer number;

    @Column(name = "name")
    private String name;

    @Column(name = "eng_name")
    private String englishName;

    @Column(name = "level")
    private Integer level;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @OneToMany(mappedBy = "rune", cascade = CascadeType.PERSIST)
    private List<RuneOption> runeOptions;

}

