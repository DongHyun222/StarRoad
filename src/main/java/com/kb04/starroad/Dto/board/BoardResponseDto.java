package com.kb04.starroad.Dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private byte[] image;
    private int commentNum;
    private String type;
    private String detailType;
    private String imageBase64; // 이미지 데이터를 저장할 필드 추가
    private String memberId; // 회원ID 저장할 필드 추가


  private List<CommentDto> comments;



}
