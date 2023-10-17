package com.game.moa.repository;

import com.game.moa.entity.Authority;
import com.game.moa.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void init() {
        Authority authority = new Authority("ROLE_USER");
        Authority authorityAdmin = new Authority("ROLE_ADMIN");
        testEntityManager.persist(new Member("test", "한정기", "gkswjdrl123@naver.com", "1234", new HashSet<>(){{{
            add(authority);
            add(authorityAdmin);
        }}}));
        testEntityManager.persist(new Member("test1", "이송희", "songhee.lee@naver.com", "1234", new HashSet<>(){{{
            add(authority);
        }}}));
        testEntityManager.persist(new Member("gamemoa", "이준기", "songhee.lee@naver.com", "1234", new HashSet<>(){{{
            add(authority);
        }}}));
    }
    @Test
    @Order(1)
    public void testMemberSave() {
        Member findMember = memberRepository.findUserByMemberId("test");
        assertThat(findMember.getMemberId()).isEqualTo("test");
        assertThat(findMember.getName()).isEqualTo("한정기");
        assertThat(findMember.getPassword()).isEqualTo("1234");
        assertThat(findMember.getEmail()).isEqualTo("gkswjdrl123@naver.com");
        assertThat(findMember.getSeq()).isEqualTo(1L);
        assertThat(findMember.getAuthorities().size()).isEqualTo(2);
    }

    @Test
    public void testMemberFindByIdContainingAndEmail() {
        List<Member> memberList = memberRepository.findMemberByMemberIdContainingAndEmail("tes", "songhee.lee@naver.com");
        assertThat(memberList.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    public void testMemberDelete() {
        memberRepository.deleteByMemberId("test");
        List<Member> afterMemberList = memberRepository.findAll();
        assertThat(afterMemberList.size()).isEqualTo(2);
    }

    @Test
    public void testMemberUpdate() {
        Member findMember = memberRepository.findUserByMemberId("test");
        findMember.setName("한정기2");
        memberRepository.save(findMember);
        Member findMember2 = memberRepository.findUserByMemberId("test");
        assertThat(findMember2.getName()).isEqualTo("한정기2");
    }

}