package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/starroad/member")
    public ModelAndView member() {
        ModelAndView mav = new ModelAndView("member/member");
        return mav;
    }

    @PostMapping("/starroad/member")
    public String member(
            @ModelAttribute MemberDto dto, RedirectAttributes redirectattributes) {
        ModelAndView mav = new ModelAndView();


        return "redirect:/login";
    }

    // 아이디 중복 확인을 위한 엔드포인트
    @RequestMapping("/starroad/checkMemberId")
    //@ResponseBody ajax 값을 바로jsp 로 보내기위해 사용
    public String checkId(@RequestParam("id") String id) {
        String result="Y";

        Member optionalMember = memberService.checkId(id);
        if (optionalMember == null) {
            // 아이디가 존재하는 경우
            result = "N";
        }
        //아이디가 있을시 Y 없을시 N으로jsp view 로 보냄
        return result;
    }

}