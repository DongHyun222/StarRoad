package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.PaymentLogDto;
import com.kb04.starroad.Dto.SubscriptionDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.*;
import com.kb04.starroad.Repository.*;
import com.kb04.starroad.Dto.MypageResponseDto;

import com.kb04.starroad.Dto.board.BoardResponseDto;

import com.kb04.starroad.Repository.Specification.BoardSpecification;
import com.kb04.starroad.Repository.Specification.CommentSpecification;
import com.kb04.starroad.Repository.Specification.PaymentLogSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PaymentLogRepository paymentLogRepository;

    public MypageResponseDto getAssets(int no) {
        MypageResponseDto mypageResponseDto = new MypageResponseDto();
        Member member = memberRepository.findByNo(no);

        mypageResponseDto.setName(member.getName());
        mypageResponseDto.setPoint(member.getPoint());
        mypageResponseDto.setInvestment(member.getInvestment());
        
        //여기 수정해야됨
        mypageResponseDto.setSavings(memberRepository.getSavings(1));
        mypageResponseDto.setDeposit(memberRepository.getDeposit(1));

        return mypageResponseDto;
    }

    public Member checkId(String id) {
        return memberRepository.findById(id);
    }

    public boolean checkPassword(int no, String inputPw) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordMatches = encoder.matches(inputPw, memberRepository.findByNo(no).getPassword());

        return passwordMatches;
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

    public List<SubscriptionDto> getSubscriptions(MemberDto memberDto) {
        return subscriptionRepository.findByMember(memberDto.toMemberEntity()).stream().map(Subscription::toSubscriptionDto).collect(Collectors.toList());
    }

    public String getPayLog(int sub_no, int period) {
        Specification<PaymentLog> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(PaymentLogSpecification.getPayLogsBySubNo(sub_no));

        int[] result = new int[period];
        List<PaymentLogDto> paylogs = paymentLogRepository.findAll(spec).stream().map(PaymentLog::toPaymentLogDto).collect(Collectors.toList());
        LocalDate first = null;    // 처음 납부 날짜
        for(PaymentLogDto paylog:paylogs) {
            LocalDate day = new java.sql.Date(paylog.getPaymentDate().getTime()).toLocalDate();
            if (first == null) {
                first = day;
            }
            result[(int) ChronoUnit.MONTHS.between(first,day)] = day.getDayOfWeek().getValue();
        }
        return Arrays.toString(result);
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

    public Member checkEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public void memberPasswordUpdate(MemberDto memberDto, String encPass) {
        memberRepository.findByIdAndUpdatePassword(memberDto.getId(), encPass);
    }
}
