package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.MypageResponseDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Repository.BoardRepository;
import com.kb04.starroad.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public List<BoardResponseDto> makeWritingResponseDtoList(List<Board> writings) {
        List<BoardResponseDto> list = new ArrayList<>();
        for (Board board : writings) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .no(board.getNo())
                    .title(board.getTitle())
                    .regdate(board.getRegdate())
                    .content(board.getContent())
                    .likes(board.getLikes())
                    .commentNum(board.getCommentNum())
                    .type(board.getType())
                    .detailType(board.getDetailType())
                    .build();
            list.add(dto);
        }
        return list;
    }

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

    public List<BoardResponseDto> getWritings(int no) {
        Member member = memberRepository.findByNo(no);
        return makeWritingResponseDtoList(boardRepository.findAllByMemberNoOrderByRegdate(member));
    }
}