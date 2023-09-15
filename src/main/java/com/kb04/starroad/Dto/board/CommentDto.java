package com.kb04.starroad.Dto.board;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Comment;
import com.kb04.starroad.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private int no;
    private Board board;
    private Member member;
    private Date regdate;
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .regdate(regdate)
                .board(board)
                .member(member)
                .content(content)
                .status('Y')
                .build();
    }
}