package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.policy.PolicyDto;
import com.kb04.starroad.Entity.Policy;
import com.kb04.starroad.Repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    public List<PolicyDto> selectAllPolicies(){

        List<PolicyDto> result = new ArrayList<>();
        List<Policy> plist = policyRepository.findAll();

        for(Policy policy : plist){
            PolicyDto dto = PolicyDto.builder()
                    .name(policy.getName())
                    .explain(policy.getExplain())
                    .location(policy.getLocation())
                    .tag(policy.getTag())
                    .link(policy.getLink())
                    .build();
            result.add(dto);
        }

        return result;
    }
}
