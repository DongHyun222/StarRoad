package com.kb04.starroad.Entity;

import com.kb04.starroad.Dto.board.BoardRequestDto;
import com.kb04.starroad.Dto.board.BoardResponseDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

    @Entity
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "board")
    public class Board {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq")
        @SequenceGenerator(name = "board_seq", sequenceName = "BOARD_SEQ", allocationSize = 1)
        @Column(name = "no")
        private int no;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "member_no")
        private Member member;

        @Column(name = "title", length = 50, nullable = false)
        private String title;

        @Column(name = "regdate", nullable = false)
        private Date regdate;

        @Column(name = "content", length = 2000, nullable = false)
        private String content;

        @Column(name = "likes")
        private int likes;

        @Column(name = "comment_num", nullable = false)
        private int commentNum = 0;

        @Column(columnDefinition = "char(1)  default 'Y'", name = "status", nullable = false)
        private Character status;

        @Column(name = "type", length = 1, nullable = false)
        private String type;

        @Lob
        @Column(name = "image")
        private byte[] image;

        @Column(name = "detail_type", length = 100, nullable = false)
        private String detailType;

        @PrePersist
        protected void onCreate() {
            regdate = new Date(); // 현재 날짜와 시간을 설정
            // status 필드가 null인 경우 '1'로 초기화
            if (status == null) {
                status = '1';
            }
        }

        public BoardRequestDto toBoardRequestDto() {
            return BoardRequestDto.builder()
                    .memberNo(member.toMemberDto())
                    .title(title)
                    .regdate(regdate)
                    .content(content)
                    .likes(likes)
                    .commentNum(commentNum)
                    .status(status)
                    .type(type)
                    .image(image)
                    .detailType(detailType)
                    .build();
        }

        public BoardResponseDto toBoardResponseDto() {
            return BoardResponseDto.builder()
                    .no(no)
                    .title(title)
                    .regdate(regdate)
                    .content(content)
                    .likes(likes)
                    .commentNum(commentNum)
                    .type(type)
                    .detailType(detailType)
                    .build();
        }
    }
