package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.policy.PolicyRequestDto;
import com.kb04.starroad.Dto.policy.PolicyResponseDto;
import com.kb04.starroad.Entity.Policy;
import com.kb04.starroad.Repository.PolicyRepository;
import com.kb04.starroad.Service.PolicyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@Api(tags = "청년정책 API")
@RequiredArgsConstructor
@RestController
public class PolicyController {

    private final PolicyService policyService;

    @ApiOperation(value = "청년정책 조회", notes = "청년정책을 조회할 수 있다")
    @GetMapping("/starroad/policy")
    public ModelAndView policy(Model model, @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) {

        List<PolicyResponseDto> result = policyService.selectAllPolicies();
        Map<String, Object> finalResult = policyService.returnPoliciesByPage(result, pageIndex);

        model.addAttribute("policyList", finalResult.get("policyList"));
        model.addAttribute("pageEndIndex", finalResult.get("pageEndIndex"));
        model.addAttribute("currentPage", pageIndex);

        ModelAndView mav = new ModelAndView("policy/policy");

        return mav;
    }

    @ApiOperation(value = "청년정책 검색", notes = "청년정책을 조건을 이용하여 검색할 수 있다")
    @GetMapping("/starroad/policy/result")
    public ModelAndView getPolicyByForm(Model model, @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex,
                                        @RequestParam(required = false) String location,
                                        @RequestParam(required = false) String keyword,
                                        @RequestParam(required = false) String tag1,
                                        @RequestParam(required = false) String tag2,
                                        @RequestParam(required = false) String tag3,
                                        @RequestParam(required = false) String tag4) {

        ModelAndView mav = new ModelAndView("policy/policy_result");
        PolicyRequestDto requestDto = PolicyRequestDto.builder()
                .location(((location == null || location.equals(""))? null : location))
                .tag1(((tag1 == null || tag1.equals(""))? null : tag1))
                .tag2(((tag2 == null || tag2.equals(""))? null : tag2))
                .tag3(((tag3 == null || tag3.equals(""))? null : tag3))
                .tag4(((tag4 == null || tag4.equals(""))? null : tag4 + " 형성"))
                .keyword(((keyword == null || keyword.equals(""))? null : keyword))
                .build();

        if(policyService.judgePolicies(requestDto)){ //아무것도 입력하지 않은 경우

            List<PolicyResponseDto> result = policyService.selectAllPolicies();
            Map<String, Object> finalResult = policyService.returnPoliciesByPage(result, pageIndex);

            model.addAttribute("policyList", finalResult.get("policyList"));
            model.addAttribute("pageEndIndex", finalResult.get("pageEndIndex"));
            model.addAttribute("currentPage", pageIndex);

        }
        else {

            List<PolicyResponseDto> result = policyService.selectDetailPolicies(requestDto);
            Map<String, Object> finalResult = policyService.returnPoliciesByPage(result, pageIndex);

            model.addAttribute("policyList", finalResult.get("policyList"));
            model.addAttribute("pageEndIndex", finalResult.get("pageEndIndex"));
            model.addAttribute("currentPage", pageIndex);

            Map<String, String> requests = policyService.mappingRequest(requestDto);
            for (String key: requests.keySet()) {
                if(key.equals("request_tag4") && requests.get(key) != null)
                    model.addAttribute(key, "금융자산");
                else
                    model.addAttribute(key, requests.get(key));
            }
        }
        return mav;
    }

}
