package com.kb04.starroad.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CommentController {
    @GetMapping("/starroad/comment")
    public ModelAndView comment() {
        ModelAndView mav = new ModelAndView("comment/comment");
        return mav;
    }
}
