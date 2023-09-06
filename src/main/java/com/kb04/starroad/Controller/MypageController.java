package com.kb04.starroad.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MypageController {
    @GetMapping("/starroad/mypage/asset")
    public ModelAndView asset() {
        ModelAndView mav = new ModelAndView("mypage/asset");
        return mav;
    }

    @GetMapping("/starroad/mypage/board")
    public ModelAndView board() {
        ModelAndView mav = new ModelAndView("mypage/board");
        return mav;
    }

    @GetMapping("/starroad/mypage/challenge")
    public ModelAndView challenge() {
        ModelAndView mav = new ModelAndView("mypage/challenge");
        return mav;
    }

    @GetMapping("/starroad/mypage/info")
    public ModelAndView info() {
        ModelAndView mav = new ModelAndView("mypage/info");
        return mav;
    }

    @GetMapping("/starroad/mypage/password")
    public ModelAndView password() {
        ModelAndView mav = new ModelAndView("mypage/password");
        return mav;
    }
}