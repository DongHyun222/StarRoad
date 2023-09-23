package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@Api(tags = {"홈 컨트롤러 입니다"})
@RestController
public class HomeController {

    @ApiOperation(value = "home", notes = "홈")
    @GetMapping("/starroad")
    public ModelAndView home(@ApiIgnore HttpSession session) {

        ModelAndView mav;
        if (session.getAttribute("currentUser") == null) {
            session.removeAttribute("modal");
            mav = new ModelAndView("home");
        } else {
            mav = new ModelAndView("LogInHome");
            MemberDto dto = (MemberDto) session.getAttribute("currentUser");
            mav.addObject("currentUser", dto.getName());
        }
        return mav;
    }

    @ApiOperation(value = "home", notes = "홈")
    @GetMapping("/starroad/starroad")
    public String home_api() {
        // prefix: /WEB-INF/views
        // suffix: .jsp
        // 풀경로: /WEB-INF/views/test.jsp
        return "home";
    }
}
