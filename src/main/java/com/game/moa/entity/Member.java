package com.game.moa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", unique = true, nullable = false, updatable = false)
    private Long seq;

    @Column(name = "member_id", nullable = false, length = 20, unique = true, updatable = false)
    private String memberId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @ManyToMany(cascade = {
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_seq", referencedColumnName = "seq")},
            inverseJoinColumns = {@JoinColumn(name = "authority_seq", referencedColumnName = "seq")})
    private Set<Authority> authorities;

    protected Member() {
    }

    public Member (String memberId, String name, String email, String password, Set<Authority> authorities) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
}
