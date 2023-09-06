package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.BoardDto;
import com.kb04.starroad.Repository.BoardRepository;
import com.kb04.starroad.Service.BoardService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BoardController2 {

    @Autowired
    private BoardService2 boardService;
    @GetMapping("/starroad/boardWrite")
    public ModelAndView board() {
        ModelAndView mav = new ModelAndView("board/boardWrite");
        return mav;
    }

    @GetMapping("/starroad/freeboard")
    public ModelAndView freeBoard(){
        ModelAndView mav =new ModelAndView("board/freeBoard");
        return mav;
    }
    @GetMapping("/starroad/boardList")
    public List<BoardDto>  boardList(){
        List<BoardDto> boardList = boardService.boardList();
        return boardList;
    }
    @PostMapping("/starroad/writepro")
    public String boardWritePro(BoardDto board){

        System.out.println(board.getTitle());
        boardService.write(board);

        return "";
}
}
