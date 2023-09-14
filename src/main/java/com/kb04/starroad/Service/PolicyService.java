package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.policy.PolicyRequestDto;
import com.kb04.starroad.Dto.policy.PolicyResponseDto;
import com.kb04.starroad.Entity.Policy;
import com.kb04.starroad.Repository.PolicyRepository;
import com.kb04.starroad.Repository.Specification.PolicySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private static final int ITEMS_PER_PAGE = 3;

    /**
     * 청년정책 전체 조회
     * @return List<PolicyResponseDto>
     */
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

    /**
     * 청년정책 Pagination
     * @return Map<String, Object>
     */
    public Map<String, Object> returnPoliciesByPage(List<PolicyResponseDto> policyList, int pageIdx) {

        Map<String, Object> result = new HashMap<>();

        int totalCount = policyList.size();
        int startIndex = (pageIdx - 1) * ITEMS_PER_PAGE;
        int endIndex = Math.min(startIndex + ITEMS_PER_PAGE, totalCount);

        result.put("policyList", policyList.subList(startIndex, endIndex));
        result.put("pageEndIndex", Math.ceil(totalCount / (double) ITEMS_PER_PAGE));

        return result;
    }

    /**
     * 청년정책 검색 조건 여부 판단
     * @return boolean
     */
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

    /**
     * 청년정책 검색 using 조건
     * @return List<PolicyResponseDto>
     */
    public List<PolicyResponseDto> selectDetailPolicies(PolicyRequestDto request) {

        Map<String, Object> searchKeys = new HashMap<>();

        if(request.getLocation() != null) searchKeys.put("location", request.getLocation());
        if(request.getKeyword() != null) searchKeys.put("keyword", request.getKeyword());

        List<String> tags = new ArrayList<>();
        if(request.getTag1() != null) tags.add(request.getTag1());
        if(request.getTag2() != null) tags.add(request.getTag2());
        if(request.getTag3() != null) tags.add(request.getTag3());
        if(request.getTag4() != null) tags.add(request.getTag4());

        if(tags.size() != 0) searchKeys.put("tag", tags);

        List<Policy> result = policyRepository.findAll(PolicySpecification.searchPolicyWithMultiConditions(searchKeys));
        List<PolicyResponseDto> finalResult = new ArrayList<>();
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

    /**
     * 청년정책 검색 조건 매핑
     * @return Map<String, String>
     */
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

}
