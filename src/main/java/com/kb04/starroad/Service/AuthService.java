package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.auth.LoginRequestDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private MemberRepository memberRepository;

    // 로그인 기능
    public MemberDto authenticate(LoginRequestDto loginRequestDto) {
        String id = loginRequestDto.getId();
        String password = loginRequestDto.getPassword();

        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isPresent()) {
            MemberDto memberDto = optionalMember.get().toMemberDto();
            // 데이터베이스에서 가져온 암호화된 비밀번호
            String encryptedPasswordFromDatabase = memberDto.getPassword();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            // 입력한 비밀번호와 데이터베이스에서 가져온 암호화된 비밀번호를 비교
            boolean passwordMatches = encoder.matches(password, encryptedPasswordFromDatabase);

            if (passwordMatches)    // 비밀번호가 일치하면 해당 회원 정보를 반환
                return memberDto;
        }
        // 아이디가 없거나 비밀번호가 일치하지 않을 경우 null 반환
        return null;
    }
}
