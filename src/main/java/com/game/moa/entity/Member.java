package com.game.moa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_seq", unique = true, nullable = false, updatable = false)
    private Long memberSeq;

    @Column(name = "member_id", nullable = false, length = 20, unique = true, updatable = false)
    private String memberId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Set<MemberAuthority> memberAuthorities;

    protected Member() {
    }

    public Member (String memberId, String name, String email, String password, Set<MemberAuthority> memberAuthorities, LocalDateTime created, LocalDateTime updated) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = created;
        this.updated = updated;
        this.memberAuthorities = memberAuthorities;
    }
}
