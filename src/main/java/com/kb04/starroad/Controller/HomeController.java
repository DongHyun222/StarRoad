package com.kb04.starroad.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = {"홈 컨트롤러 입니다"})
@RestController
public class HomeController {

    @ApiOperation(value = "home", notes = "홈")
    @GetMapping("/starroad")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }

    @ApiOperation(value = "home", notes = "홈")
    @GetMapping("/api/starroad")
    public String home_api() {
        // prefix: /WEB-INF/views
        // suffix: .jsp
        // 풀경로: /WEB-INF/views/test.jsp
        return "home";
    }
}
