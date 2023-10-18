package com.game.moa.service;

import com.game.moa.entity.Member;
import com.game.moa.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    public LoginServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findUserByMemberId(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return null;
    }
}
