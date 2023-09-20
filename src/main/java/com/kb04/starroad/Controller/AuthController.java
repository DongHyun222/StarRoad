package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.auth.LoginRequestDto;
import com.kb04.starroad.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/starroad/login")
    public ModelAndView showLoginForm() {
        ModelAndView mav = new ModelAndView("member/login");
        return mav;
    }
    @PostMapping("/starroad/login")
    public ModelAndView login(LoginRequestDto requestDto, HttpSession session, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();

        if (requestDto.getId().trim().isEmpty()) {
            mav.setViewName("redirect:/starroad/login");
            redirectAttributes.addFlashAttribute("error", "아이디를 입력해주세요");
        } else if (requestDto.getPassword().trim().isEmpty()) {
            mav.setViewName("redirect:/starroad/login");
            redirectAttributes.addFlashAttribute("written_id", requestDto.getId());
            redirectAttributes.addFlashAttribute("error", "비밀번호를 입력해주세요");
        } else {
            MemberDto memberDto = authService.authenticate(requestDto);
            if (memberDto != null) {
                session.setAttribute("currentUser", memberDto);
                mav.setViewName("redirect:/starroad"); // 로그인 성공
            } else {
                mav.setViewName("redirect:/starroad/login"); // 로그인 실패
                redirectAttributes.addFlashAttribute("error", "아이디와 비밀번호가 일치하지 않습니다");
            }
        }
        return mav;
    }

    @GetMapping("/starroad/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();  // 세션 정보를 모두 삭제
        ModelAndView mav = new ModelAndView("redirect:/starroad");
        return mav;
    }
}