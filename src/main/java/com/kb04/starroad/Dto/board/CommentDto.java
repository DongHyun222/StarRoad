package com.kb04.starroad.Dto.board;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Comment;
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
    private BoardRequestDto board;
    private MemberDto member;
    private Date regdate;
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .no(no)
                .regdate(regdate)
                .content(content)
                .build();
    }
}