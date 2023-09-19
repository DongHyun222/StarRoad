package com.kb04.starroad.Controller;


import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/starroad/member")
    public ModelAndView member() {
        ModelAndView mav = new ModelAndView("member/member");
        return mav;
    }

    @PostMapping("/starroad/member")
    public ModelAndView member(
            @ModelAttribute MemberDto dto) {
        ModelAndView mav = new ModelAndView("redirect:/starroad/login");

        dto.setStatus('Y');
        dto.setAgreement('Y');
        String num = dto.getPhone().replace(",", "-");
        dto.setPhone(num);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encPass = encoder.encode(dto.getPassword());
        dto.setPassword(encPass);

        memberService.memberInsert(dto);

        return mav;
    }

    // 아이디 중복 확인을 위한 엔드포인트
    @RequestMapping("/starroad/checkMemberId")
    public String checkId(@RequestParam("id") String id) {
        String result="Y";
        Member optionalMember = memberService.checkId(id);
        if (optionalMember == null) {
            // 아이디가 존재하지 않는 경우
            result = "N";
        }
        //아이디가 있을시 Y 없을시 N으로 jsp view 로 보냄
        return result;
    }

//    @RequestMapping("/starroad/checkMemberEmail")
//    public String checkEmail(@RequestParam("email") String email) {
//        String result="Y";
//        Member optionalMember = memberService.checkEmail(email);
//        if (optionalMember == null) {
//            // 아이디가 존재하지 않는 경우
//            result = "N";
//        }
//        //아이디가 있을시 Y 없을시 N으로 jsp view 로 보냄
//        return result;
//    }

}