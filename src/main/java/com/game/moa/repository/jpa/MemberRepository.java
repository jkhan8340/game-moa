package com.game.moa.repository.jpa;

import com.game.moa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findUserByMemberId(String memberId);

    List<Member> findMemberByMemberIdContainingAndEmail(String id, String email);

    @Query(value = """
    select distinct m
    from Member m
    join fetch m.memberAuthorities ma
    join fetch ma.authority a
    """)
    List<Member> findAllMemberForAuthorities();

    void deleteByMemberId(String memberId);
}
