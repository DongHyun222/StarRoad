package com.kb04.starroad.Controller;

import com.kb04.starroad.Dto.PolicyDto;
import com.kb04.starroad.Repository.PolicyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;

@RestController
public class PolicyController {

    private final PolicyRepository policyRepository;

    public PolicyController(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @GetMapping("/starroad/policy")
    public ModelAndView policy(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex) {
        ModelAndView mav = new ModelAndView("policy/policy");

        PageRequest pageRequest = PageRequest.of(pageIndex, 3);
        Page<PolicyDto> list = policyRepository.findAll(pageRequest);
        int totalCount = list.getSize();

        mav.addObject("list", list);
        mav.addObject("totalCount", totalCount);

        return mav;
    }

    /*
    @PostConstruct
    public void initializing() {
        for(int i=0; i<24; i++){
            PolicyDto dto = PolicyDto.builder()
                    .name("name" + i)
                    .explain("explain" + i)
                    .link("link" + i)
                    .tag("tag" + i)
                    .location("loca" + i)
                    .build();
            policyRepository.save(dto);
        }
    }
    */
}
