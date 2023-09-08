package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.auth.LoginRequestDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.login.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/starroad/auth/login")
    public ModelAndView showLoginForm() {
        ModelAndView mav = new ModelAndView("member/login");
        
        return mav;
    }
    @PostMapping("/starroad/auth/login")
    public ModelAndView login(LoginRequestDto requestDto, HttpSession session) {
        Member member = authService.authenticate(requestDto.getId(), requestDto.getPw());
        if (member != null) {
            session.setAttribute("currentUser", member);
            return new ModelAndView("home");
        } else {
            ModelAndView mav = new ModelAndView("member/login");
            mav.addObject("error", "Invalid ID or password");
            return mav;
        }
    }

    @GetMapping("/starroad/auth/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "starroad/auth/login";
    }
}