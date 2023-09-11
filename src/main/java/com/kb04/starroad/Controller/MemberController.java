package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MemberController {
    @GetMapping("/starroad/member")
    public ModelAndView member() {
        ModelAndView mav = new ModelAndView("member/member");
        return mav;
    }

    @PostMapping("/starroad/member")
    public String member(
            @ModelAttribute MemberDto dto) {
        ModelAndView mav = new ModelAndView();


        return "redirect:login";
    }

}