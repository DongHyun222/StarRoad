package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;

    private static Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("currentUser");
        return loginMember;
    }

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

    //회원정보 수정하는 부분
    @PostMapping("/starroad/mypage/info")
    public ModelAndView info(
            HttpServletRequest request,
            @ModelAttribute MemberDto changeDto) {
        ModelAndView mav = new ModelAndView("redirect:/starroad");

        MemberDto memberDto = getLoginMember(request).toMemberDto();

        mav.addObject("member", memberDto);

        memberService.memberUpdate(memberDto, changeDto);

        return mav;
    }


    @GetMapping("/starroad/mypage/password")
    public ModelAndView password() {
        ModelAndView mav = new ModelAndView("mypage/password");
        return mav;
    }

    @PostMapping("/api/starroad/mypage/check-password")
    public String checkPassword(@RequestParam("inputPw") String inputPw) {
        String msg = "";
        if (!memberService.checkPassword(1, inputPw)) {
            msg = "비밀번호를 잘못 입력했습니다. 다시 입력해주세요.";
        }
        return msg;
    }
}