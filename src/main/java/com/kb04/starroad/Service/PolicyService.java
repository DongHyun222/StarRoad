package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.policy.PolicyRequestDto;
import com.kb04.starroad.Dto.policy.PolicyResponseDto;
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

    public List<PolicyResponseDto> selectAllPolicies(){

        List<PolicyResponseDto> result = new ArrayList<>();
        List<Policy> plist = policyRepository.findAll();

        for(Policy policy : plist){
            PolicyResponseDto dto = PolicyResponseDto.builder()
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

    public boolean judgePolicies(PolicyRequestDto requestDto) {

        PolicyRequestDto dto = PolicyRequestDto.builder()
                .keyword(null)
                .location(null)
                .tag1(null)
                .tag2(null)
                .tag3(null)
                .tag4(null)
                .build();

        return dto.equals(requestDto);
    }

    public List<PolicyResponseDto> selectDetailPolicies(PolicyRequestDto request) {

        List<PolicyResponseDto> result = new ArrayList<>();

        List<Policy> search = new ArrayList<>();

        List<String> tagList = new ArrayList<>();
        if(!request.getTag1().isEmpty()) tagList.add(request.getTag1());
        if(!request.getTag2().isEmpty()) tagList.add(request.getTag2());
        if(!request.getTag3().isEmpty()) tagList.add(request.getTag3());
        if(!request.getTag4().isEmpty()) tagList.add(request.getTag4());

        return null;
    }


}
