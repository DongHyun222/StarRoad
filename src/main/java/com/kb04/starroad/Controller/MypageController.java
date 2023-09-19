package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.SubProdDto;
import com.kb04.starroad.Dto.SubscriptionDto;
import com.kb04.starroad.Service.MemberService;
import com.kb04.starroad.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;
    private final ProductService productService;

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

    private MemberDto kiki = new MemberDto().builder()
            .investment(100)
            .address("서울 강남구 선릉로 428, 108동 101호")
            .birthday("97/12/04")
            .phone("010-1234-5678")
            .email("imki@gmail.com")
            .password("skzlzl04")
            .id("imkiki")
            .name("나키키")
            .job("직장인")
            .point(1000)
            .agreement('Y')
            .salary(1000)
            .status('Y')
            .goal(1000)
            .no(1)
            .source("근로 및 연금소득")
            .purpose("급여 및 생활비")
            .build();

    @GetMapping("/starroad/mypage/challenge")
    public ModelAndView challenge() {
        ModelAndView mav = new ModelAndView("mypage/challenge");
        List<SubscriptionDto> subscriptions = memberService.getSubscriptions(kiki);
        mav.addObject("subscriptions", subscriptions);
        List<String> paymentLogs = new ArrayList<>();
        for (SubscriptionDto sub : subscriptions) {
            paymentLogs.add(memberService.getPayLog(sub.getNo(), sub.getPeriod(), sub.getReceived()));
        }
        mav.addObject("paymentLogs", paymentLogs);
        return mav;
    }

    @PostMapping("/starroad/mypage/reward")
    public ModelAndView reward(@RequestParam("sub_no") int subNo,
                               @RequestParam("name") String name,
                               @RequestParam("period") int period) {
        ModelAndView mav = new ModelAndView("mypage/reward");
        mav.addObject("reward", memberService.getReward(period));
        mav.addObject("sub_no", subNo);
        mav.addObject("name", name);
        mav.addObject("period", period);
        return mav;
    }

    @PostMapping("/starroad/mypage/save-reward")
    public ModelAndView getReward(@RequestParam("sub_no") int subNo,
                                  @RequestParam("reward") int reward) {
        ModelAndView mav = new ModelAndView("redirect:/starroad/mypage/asset");
        memberService.saveReward(1, subNo, reward);
        return mav;
    }

    @GetMapping("/starroad/mypage/info")
    public ModelAndView info() {
        ModelAndView mav = new ModelAndView("mypage/info");
        return mav;
    }

    @GetMapping("/starroad/mypage/password")
    public ModelAndView password() {
        ModelAndView mav = new ModelAndView("mypage/password");
        return mav;
    }

    @PostMapping("/starroad/mypage/password")
    public ModelAndView password(
            @RequestParam("password") String password,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("redirect:/starroad");

        MemberDto memberDto = getLoginMember(request);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encPass = encoder.encode(password);

        memberService.memberPasswordUpdate(memberDto,encPass);

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