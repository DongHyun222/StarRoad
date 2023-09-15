package com.kb04.starroad.Dto.board;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Entity.Board;
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

        public class BoardRequestDto {
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

            public Board toEntity() { //Dto를 Entity로 변환
                return Board.builder()


                        .title(this.title)
                        .content(this.content)
                        .likes(this.likes)
                        .commentNum(this.commentNum)
                        .status(this.status)
                        .type(this.type)
                        .image(this.image)

                        .detailType(this.detailType)
                        .build();
            }

        //고민
        public static BoardResponseDto fromEntity(Board board) {
            BoardResponseDto dto = new BoardResponseDto();
            dto.setNo(board.getNo());
            dto.setTitle(board.getTitle());
            dto.setRegdate(board.getRegdate());
            dto.setContent(board.getContent());
            dto.setLikes(board.getLikes());
            dto.setCommentNum(board.getCommentNum());
            dto.setType(board.getType());
            dto.setDetailType(board.getDetailType());
            return dto;
        }

    }

