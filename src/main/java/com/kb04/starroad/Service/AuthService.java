package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.auth.LoginRequestDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private MemberRepository memberRepository;

    //로그인 기능
    public Member authenticate(LoginRequestDto loginRequestDto) {
        return memberRepository.findByIdAndPassword(loginRequestDto.getId(), loginRequestDto.getPassword()) .orElse(null);
    }
}
