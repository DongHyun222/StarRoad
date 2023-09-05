package com.kb04.starroad.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PolicyController {
    @GetMapping("/starroad/policy")
    public ModelAndView policy() {
        ModelAndView mav = new ModelAndView("policy/policy");
        return mav;
    }
}
