package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.MypageResponseDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public MypageResponseDto getAssets(int no) {
        MypageResponseDto mypageResponseDto = new MypageResponseDto();
        Member member = memberRepository.findByNo(no);

        mypageResponseDto.setName(member.getName());
        mypageResponseDto.setPoint(member.getPoint());
        mypageResponseDto.setInvestment(member.getInvestment());
        mypageResponseDto.setSavings(memberRepository.getSavings(no));
        mypageResponseDto.setDeposit(memberRepository.getDeposit(no));

        return mypageResponseDto;
    }

    public Member checkId(String id) {
        return memberRepository.findById(id);
    }

}
