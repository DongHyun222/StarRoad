package com.kb04.starroad.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BoardController1 {

    @GetMapping("/starroad/board")
    public ModelAndView board() {
        ModelAndView mav = new ModelAndView("board/board");
        return mav;
    }

}
