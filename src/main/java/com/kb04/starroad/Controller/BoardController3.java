package com.kb04.starroad.Controller;

import com.kb04.starroad.Service.BoardService3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BoardController3 {

    @Autowired
    private BoardService3 boardService3;

    @GetMapping("/starroad/boardDetail")
    public ModelAndView BoardDetail() {
        ModelAndView mav = new ModelAndView("board/boardDetail");
        return mav;
    }
}