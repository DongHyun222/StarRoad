package com.kb04.starroad.Controller;

import com.kb04.starroad.Service.BoardService2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class BoardController1 {

    private final BoardService2 boardService;

    public BoardController1(BoardService2 boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/starroad/board")
    public ModelAndView board() {
        ModelAndView mav = new ModelAndView("board/board");
        return mav;
    }

    //자유랑 인증 게시판
    @GetMapping("/starroad/board/main")
    public ModelAndView boardList() {
        ModelAndView mav = new ModelAndView("board/main");

        // PageRequest를 생성하여 정렬 조건을 설정
        PageRequest freepageRequest = PageRequest.of(0, 10, Sort.by("regdate").descending());
        PageRequest authpageRequest = PageRequest.of(0, 10, Sort.by("regdate").descending());
        PageRequest popularpageRequest = PageRequest.of(0, 10, Sort.by("regdate").descending());

        mav.addObject("freeBoard", boardService.boardListFree(freepageRequest));
        mav.addObject("authBoard", boardService.boardListAuth(authpageRequest));
        mav.addObject("popularBoard", boardService.boardListpopular(popularpageRequest));

        return mav;
    }

}
