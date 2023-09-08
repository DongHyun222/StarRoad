package com.kb04.starroad.Dto.board;

import com.kb04.starroad.Dto.MemberDto;
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
    private BoardDto board;
    private MemberDto member;
    private Date regdate;
    private String content;
}