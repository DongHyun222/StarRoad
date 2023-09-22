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
    private Member member;
    private String title;
    private Date regdate;
    private String content;
    private int likes = 0;
    private int commentNum = 0;
    private Character status = 'Y';
    private String type;
    private byte[] image;
    private String detailType;

    /**
     * Dto를 Entity로 변환
     * @return Board
     */
    public Board toEntity() {
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .likes(this.likes)
                .commentNum(this.commentNum)
                .status(this.status)
                .type(this.type)
                .image(this.image)
                .detailType(this.detailType)
                .member(this.member)
                .build();
    }

    /*//고민
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
    }*/

}

