package com.kb04.starroad.Dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {
    private int no;
    private String title;
    private Date regdate;
    private String content;
    private int likes;
    private int commentNum;
    private String type;
    private String detailType;

}
