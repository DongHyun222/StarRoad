package com.kb04.starroad.login;

import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private MemberRepository memberRepository;

    //로그인 기능
    public Member authenticate(String id, String password) {
        return memberRepository.findByIdAndPassword(id, password).orElse(null);
    }
}
