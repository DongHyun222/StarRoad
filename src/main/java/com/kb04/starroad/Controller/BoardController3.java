package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.board.BoardResponseDto;
import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Service.BoardService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;
import java.util.Optional;

@RestController
public class BoardController3 {

    @Autowired
    private BoardService2 boardService2;

    @GetMapping("/starroad/board/detail")
    public ModelAndView BoardDetail(@RequestParam Integer no) {

        ModelAndView mav = new ModelAndView();

        Optional<Board> boardOptional = boardService2.findById(no);

        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            BoardResponseDto boardResponseDto = board.toBoardResponseDto();

            // 이미지를 Base64로 인코딩하여 DTO에 추가
            if (board.getImage() != null) {
                byte[] imageBytes = board.getImage();
                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                boardResponseDto.setImageBase64(imageBase64);
            }

            mav.setViewName("board/detail");
            mav.addObject("board", boardResponseDto);
        } else {
            // 게시글이 없을 경우 처리
            mav.setViewName("board/detail");
            mav.addObject("error", "게시글을 찾을 수 없습니다.");
        }
        return mav;
    }



    @GetMapping("/starroad/board/delete")
    public ModelAndView deleteBoard(@RequestParam Integer no) {

        boardService2.deleteBoard(no);
        ModelAndView mav = new ModelAndView("redirect:/starroad/board/main");
        return mav;
    }

}