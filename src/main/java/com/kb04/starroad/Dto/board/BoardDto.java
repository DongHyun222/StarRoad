package com.kb04.starroad.Dto.board;

import com.kb04.starroad.Dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class BoardDto {
        private int no;
        private MemberDto memberNo;
        private String title;
        private Date regdate;
        private String content;
        private int likes;
        private int commentNum = 0;
        private Character status;
        private String type;
        private byte[] image;
        private String detailType;
    }
