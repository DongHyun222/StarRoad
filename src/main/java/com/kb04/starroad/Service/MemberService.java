package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Repository.*;
import com.kb04.starroad.Dto.MypageResponseDto;

import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Comment;

import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Repository.Specification.BoardSpecification;
import com.kb04.starroad.Repository.Specification.CommentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public boolean checkPassword(int no, String inputPw) {
        return memberRepository.findByNo(no).getPassword().equals(inputPw);
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
        System.out.println("*1111");
        Member member = dto.toMemberEntity();
        System.out.println("*2222");
        memberRepository.save(member);
        System.out.println("*3333");
    }

    public void memberUpdate(MemberDto memberDto, MemberDto changeDto) {
        String num = changeDto.getPhone().replace(",", "-");
        memberDto.setPhone(num);
        memberDto.setEmail(changeDto.getEmail());
        memberDto.setAddress(changeDto.getAddress());
        memberDto.setJob(changeDto.getJob() != null ? changeDto.getJob() : memberDto.getJob());
        memberDto.setSalary(changeDto.getSalary());
        memberDto.setPurpose(changeDto.getPurpose() != null ? changeDto.getPurpose() : memberDto.getPurpose());
        memberDto.setSource(changeDto.getSource() != null ? changeDto.getSource() : memberDto.getSource());
        memberDto.setGoal(changeDto.getGoal());

        Member member = memberDto.toMemberEntity();
//        memberRepository.update(member);

        memberRepository.updateMember(memberDto.getNo(), memberDto.getPhone(),
                memberDto.getEmail(), memberDto.getAddress(),
                memberDto.getJob(), memberDto.getSalary(),
                memberDto.getPurpose(), memberDto.getSource(),
                memberDto.getGoal());



    }
}
