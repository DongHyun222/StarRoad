package com.kb04.starroad.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MemberController {
    @GetMapping("/starroad/member")
    public ModelAndView member() {
        ModelAndView mav = new ModelAndView("member/member");
        return mav;
    }
}
