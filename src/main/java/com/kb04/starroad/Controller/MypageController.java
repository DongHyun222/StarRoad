package com.kb04.starroad.Controller;

import com.kb04.starroad.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;

    @GetMapping("/starroad/mypage/asset")
    public ModelAndView asset() {
        ModelAndView mav = new ModelAndView("mypage/asset");
        mav.addObject("memberAssets", memberService.getAssets(1));
        return mav;
    }

    @GetMapping("/starroad/mypage/board")
    public ModelAndView board() {
        ModelAndView mav = new ModelAndView("mypage/board");
        mav.addObject("writings", memberService.getWritings(1));
        return mav;
    }

    @GetMapping("/starroad/mypage/comment")
    public ModelAndView comment() {
        ModelAndView mav = new ModelAndView("mypage/comment");
        mav.addObject("comments", memberService.getComments(1));
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