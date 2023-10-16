package com.game.moa.repository;

import com.game.moa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findUserByMemberId(String memberId);

    List<Member> findMemberByMemberIdContainingAndEmail(String id, String email);

    void deleteByMemberId(String memberId);
}
