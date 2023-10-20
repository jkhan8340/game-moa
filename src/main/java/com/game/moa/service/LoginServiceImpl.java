package com.game.moa.service;

import com.game.moa.entity.Member;
import com.game.moa.entity.MemberAuthority;
import com.game.moa.repository.MemberRepository;
import com.game.moa.vo.AuthorityVO;
import com.game.moa.vo.MemberVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
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
        return MemberVO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
                .password(member.getPassword())
                .authorities(member.getMemberAuthorities()
                        .stream()
                        .map((memberAuthority) ->
                                AuthorityVO.builder()
                                        .authority(memberAuthority.getAuthority().getName())
                                        .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
