package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.policy.PolicyResponseDto;
import com.kb04.starroad.Service.PolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@Api(tags = {"홈 API"})
@RequiredArgsConstructor
@RestController
public class HomeController {

    private final PolicyService policyService;

    @ApiOperation(value = "home", notes = "홈")
    @GetMapping("/starroad")
    public ModelAndView home(@ApiIgnore HttpSession session) {

        ModelAndView mav;
        if (session.getAttribute("currentUser") == null) {
            session.removeAttribute("modal");
            mav = new ModelAndView("home");
        } else {
            mav = new ModelAndView("loginHome");
            MemberDto dto = (MemberDto) session.getAttribute("currentUser");

            PolicyResponseDto result = policyService.modalPolicy(dto);
            if (result == null){
                mav.addObject("message", "관심정책을 등록하고 알림을 받아보세요🤗");
            } else {
                mav.addObject("currentUser", dto.getName());
                mav.addObject("policyName", result.getName());
                mav.addObject("policyDday", result.getDDay());
            }
        }
        return mav;
    }

}
