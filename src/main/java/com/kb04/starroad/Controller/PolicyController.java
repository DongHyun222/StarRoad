package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.policy.PolicyRequestDto;
import com.kb04.starroad.Dto.policy.PolicyResponseDto;
import com.kb04.starroad.Service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.*;

@RequiredArgsConstructor
@Controller
public class PolicyController {

    private static final int ITEMS_PER_PAGE = 3;
    private final PolicyService policyService;

    @GetMapping("/starroad/policy")
    public ModelAndView policy(Model model, @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) {

        List<PolicyResponseDto> result = null;
        Map<String, ?> map = model.asMap();

        if(Objects.equals(model.getAttribute("flag"), "none") || !map.containsKey("flag")){
            result = policyService.selectAllPolicies();
        }
        else {
            System.out.println(map.get("request"));
            result = policyService.selectDetailPolicies((PolicyRequestDto) map.get("request"));
        }

        int totalCount = result.size();

        int startIndex = (pageIndex - 1) * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, totalCount);

        model.addAttribute("policyList", result.subList(startIndex, endIndex));
        model.addAttribute("pageEndIndex", Math.ceil(totalCount / (double) ITEMS_PER_PAGE));
        model.addAttribute("currentPage", pageIndex);

        ModelAndView mav = new ModelAndView("policy/policy");

        return mav;
    }

    @PostMapping("/api/starroad/policy")
    public String getPolicyByForm(@ModelAttribute PolicyRequestDto requestDto, RedirectAttributes redirectAttributes) {

        if(requestDto.getKeyword().equals("")) requestDto.setKeyword(null);

        if(policyService.judgePolicies(requestDto)){ //아무것도 입력하지 않은 경우
            redirectAttributes.addFlashAttribute("flag", "none");
        }
        else {
            redirectAttributes.addFlashAttribute("flag", "search");
            redirectAttributes.addFlashAttribute("request", requestDto);
        }
        return "redirect:/starroad/policy";
    }

}
