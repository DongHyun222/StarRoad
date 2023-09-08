package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.policy.PolicyDto;
import com.kb04.starroad.Entity.Policy;
import com.kb04.starroad.Repository.PolicyRepository;
import com.kb04.starroad.Service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PolicyController {

    private static final int ITEMS_PER_PAGE = 3;
    private final PolicyService policyService;

    @GetMapping("/starroad/policy")
    public ModelAndView policy(Model model, @RequestParam(value = "pageIndex", defaultValue = "1") int pageIndex) {

        List<PolicyDto> result = policyService.selectAllPolicies();
        int totalCount = result.size();

        int startIndex = (pageIndex - 1) * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, totalCount);

        model.addAttribute("policyList", result.subList(startIndex, endIndex));
        model.addAttribute("pageEndIndex", Math.ceil(totalCount / (double) ITEMS_PER_PAGE));
        model.addAttribute("currentPage", pageIndex);

        ModelAndView mav = new ModelAndView("policy/policy");

        return mav;
    }

}
