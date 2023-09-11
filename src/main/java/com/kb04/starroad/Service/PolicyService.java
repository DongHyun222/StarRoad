package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.policy.PolicyRequestDto;
import com.kb04.starroad.Dto.policy.PolicyResponseDto;
import com.kb04.starroad.Entity.Policy;
import com.kb04.starroad.Repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        List<Policy> search = null;

        if(request.getLocation() != null && request.getKeyword() != null){
            search = policyRepository.findByLocationAndNameContaining(request.getLocation(), request.getKeyword());
        } else if (request.getLocation() == null && request.getKeyword() != null) {
            search = policyRepository.findByNameContaining(request.getKeyword());
        } else if (request.getLocation() != null && request.getKeyword() == null) {
            search = policyRepository.findByLocation(request.getLocation());
        }

        // 태그 검색
        StringBuilder stringBuilder = new StringBuilder();
        List<String> tagList = new ArrayList<>();
        stringBuilder.append('(');
        if(request.getTag1() != null) {
            stringBuilder.append(request.getTag1());
            tagList.add(request.getTag1());
        }
        if(request.getTag2() != null) {
            stringBuilder.append(request.getTag2());
            tagList.add(request.getTag2());
        }
        if(request.getTag3() != null) {
            stringBuilder.append(request.getTag3());
            tagList.add(request.getTag3());
        }
        if(request.getTag4() != null) {
            stringBuilder.append(request.getTag4());
            tagList.add(request.getTag4());
        }
        stringBuilder.append(')');

        if(search.isEmpty()){
            search = policyRepository.findByTagList(String.valueOf(stringBuilder));
        } else {
            if(String.valueOf(stringBuilder).length() > 2)
                search.removeIf(policy -> !tagList.contains(policy.getTag()));
        }

        // 변환
        for (Policy policy:search) {
            PolicyResponseDto dto = PolicyResponseDto.builder()
                    .tag(policy.getTag())
                    .location(policy.getLocation())
                    .explain(policy.getExplain())
                    .name(policy.getName())
                    .link(policy.getLink())
                    .build();
            result.add(dto);
        }

        return result;
    }

    private List<String> findCriteria(PolicyRequestDto requestDto){

        List<String> result = new ArrayList<>();

        if(requestDto.getLocation() != null) result.add("location");
        if(requestDto.getKeyword() != null) result.add("keyword");
        if(requestDto.getTag1() != null || requestDto.getTag2() != null ||
                requestDto.getTag3() != null || requestDto.getTag4() != null)
            result.add("tag");

        return result;
    }

}
