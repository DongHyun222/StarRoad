package com.kb04.starroad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BoardDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq")
    @SequenceGenerator(name = "board_seq", sequenceName = "BOARD_SEQ", allocationSize = 1)
    @Column(name = "no")
    private int no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private MemberDto memberNo;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "regdate", nullable = false)
    private Date regdate;

    @Column(name = "content", length = 2000, nullable = false)
    private String content;

    @Column(name = "like")
    private int like;

    @Column(name = "comment_num", nullable = false)
    private int commentNum = 0;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "type", length = 1, nullable = false)
    private String type;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "detail_type", length = 100, nullable = false)
    private String detailType;
}
