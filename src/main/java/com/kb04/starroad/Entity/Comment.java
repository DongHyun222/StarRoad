package com.kb04.starroad.Entity;

import com.kb04.starroad.Dto.board.CommentDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "COMMENT_SEQ", allocationSize = 1)
    @Column(name = "no")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @Column(name = "regdate", nullable = false)
    private Date regdate;

    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    public CommentDto toCommentDto() {
        return CommentDto.builder()
                .no(no)
                .board(board.toBoardRequestDto())
                .member(member.toMemberDto())
                .regdate(regdate)
                .content(content)
                .build();
    }
}