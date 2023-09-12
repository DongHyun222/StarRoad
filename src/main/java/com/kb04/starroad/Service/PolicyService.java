package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.policy.PolicyRequestDto;
import com.kb04.starroad.Dto.policy.PolicyResponseDto;
import com.kb04.starroad.Entity.Policy;
import com.kb04.starroad.Repository.PolicyRepository;
import com.kb04.starroad.Repository.PolicySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private static final int ITEMS_PER_PAGE = 3;

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

    public Map<String, Object> returnPoliciesByPage(List<PolicyResponseDto> policyList, int pageIdx) {

        Map<String, Object> result = new HashMap<>();

        int totalCount = policyList.size();
        int startIndex = (pageIdx - 1) * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, totalCount);

        result.put("policyList", policyList.subList(startIndex, endIndex));
        result.put("pageEndIndex", Math.ceil(totalCount / (double) ITEMS_PER_PAGE));

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

    public Map<String, String> mappingRequest(PolicyRequestDto requestDto) {

        Map<String, String> result = new HashMap<>();

        result.put("request_location", requestDto.getLocation());
        result.put("request_keyword", requestDto.getKeyword());
        result.put("request_tag1", requestDto.getTag1());
        result.put("request_tag2", requestDto.getTag2());
        result.put("request_tag3", requestDto.getTag3());
        result.put("request_tag4", requestDto.getTag4());

        return result;
    }

    public List<PolicyResponseDto> selectDetailPolicies(PolicyRequestDto request) {

        Map<String, Object> searchKeys = new HashMap<>();
        List<PolicyResponseDto> finalResult = new ArrayList<>();

        if(request.getLocation() != null) searchKeys.put("location", request.getLocation());
        if(request.getKeyword() != null) searchKeys.put("keyword", request.getKeyword());
        List<String> tags = new ArrayList<>();
        if(request.getTag1() != null) tags.add(request.getTag1());
        if(request.getTag2() != null) tags.add(request.getTag2());
        if(request.getTag3() != null) tags.add(request.getTag3());
        if(request.getTag4() != null) tags.add(request.getTag4());

        if(tags.size() != 0) searchKeys.put("tag", tags);

        List<Policy> result = policyRepository.findAll(PolicySpecification.searchPolicyWithMultiConditions(searchKeys));
        for(Policy policy : result){
            PolicyResponseDto dto = PolicyResponseDto.builder()
                    .name(policy.getName())
                    .explain(policy.getExplain())
                    .tag(policy.getTag())
                    .link(policy.getLink())
                    .location(policy.getLocation())
                    .build();
            finalResult.add(dto);
        }
        return finalResult;
    }

}
