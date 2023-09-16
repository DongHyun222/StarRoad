package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Dto.board.CommentDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Service.BoardService2;
import com.kb04.starroad.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
public class    BoardController3 {

    @Autowired
    private BoardService2 boardService2;

    @Autowired
    private CommentService commentService;


    @GetMapping("/starroad/board/delete")
    public ModelAndView deleteBoard(@RequestParam Integer no, HttpSession session) {
        ModelAndView mav = new ModelAndView();

        Member currentUser = (Member) session.getAttribute("currentUser");
        String currentUserId = currentUser.getId();

        if (boardService2.canDelete(no, currentUserId)) {
            // 현재 사용자가 게시글 삭제 가능한 경우
            boardService2.deleteBoard(no);
            mav.setViewName("redirect:/starroad/board/main");

        } else{
            // 현재 사용자가 게시글 삭제 불가능한 경우 or 게시글 존재하지 않는 경우
            mav.setViewName("board/deleteError");
        }

        return mav;
    }
    @GetMapping("/starroad/board/detail")
    public ModelAndView getBoardDetail(@RequestParam("no") Integer no) {

        ModelAndView mav = new ModelAndView("board/detail");

        Optional<Board> boardOptional = boardService2.findById(no);

        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();

            List<CommentDto> comments = commentService.findByBoard(board);

            BoardResponseDto boardResponseDto = board.toBoardResponseDto();
            boardResponseDto.setComments(comments);

            // 이미지를 Base64로 인코딩하여 DTO에 추가
            if (board.getImage() != null) {
                byte[] imageBytes = board.getImage();
                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                boardResponseDto.setImageBase64(imageBase64);
            }

            if (boardResponseDto.getComments() == null || boardResponseDto.getComments().isEmpty()) {
                mav.addObject("noComments", true);
            }

            mav.addObject("board", boardResponseDto);
        } else {
            mav.addObject("error", "게시글을 찾을 수 없습니다.");
        }

        return mav;
    }


}