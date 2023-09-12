package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Repository.*;
import com.kb04.starroad.Dto.MypageResponseDto;

import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Comment;

import com.kb04.starroad.Entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

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


    public List<BoardResponseDto> getWritings(int no) {
        Specification<Board> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(BoardSpecification.writtenByUser(no));
        return boardRepository.findAll(spec).stream().map(Board::toBoardResponseDto).collect(Collectors.toList());
    }

    public List<CommentDto> getComments(int no) {
        Specification<Comment> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(CommentSpecification.writtenByUser(no));
        return commentRepository.findAll(spec).stream().map(Comment::toCommentDto).collect(Collectors.toList());
    }

    public void memberInsert(MemberDto dto) {
        Member member = dto.toMemberEntity();
        memberRepository.save(member);
    }
}
