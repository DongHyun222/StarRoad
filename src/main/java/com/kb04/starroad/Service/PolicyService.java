package com.kb04.starroad.Service;

import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Dto.policy.PolicyRequestDto;
import com.kb04.starroad.Dto.policy.PolicyResponseDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Entity.Policy;
import com.kb04.starroad.Entity.PolicyHeart;
import com.kb04.starroad.Repository.MemberRepository;
import com.kb04.starroad.Repository.PolicyHeartRepository;
import com.kb04.starroad.Repository.PolicyRepository;
import com.kb04.starroad.Repository.Specification.PolicySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final PolicyHeartRepository policyHeartRepository;
    private final MemberRepository memberRepository;
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
                    .no(policy.getNo())
                    .name(policy.getName())
                    .explain(policy.getExplain())
                    .location(policy.getLocation())
                    .tag(policy.getTag())
                    .link(policy.getLink())
                    .isLiked("N")
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
                    .no(policy.getNo())
                    .name(policy.getName())
                    .explain(policy.getExplain())
                    .tag(policy.getTag())
                    .link(policy.getLink())
                    .location(policy.getLocation())
                    .isLiked("N")
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

    /**
     * 로그인한 유저의 관심 정책 표시
     * @param list 필터링한 정책 리스트
     * @param memberDto 현재 로그인한 유저
     */
    public List<PolicyResponseDto> mappingPolicyHeart(List<PolicyResponseDto> list, MemberDto memberDto) {

        int memberNo = memberDto.getNo();
        List<PolicyHeart> heartList = policyHeartRepository.findAllByMemberNo(memberNo);

        if(heartList.equals(null))
            return list;
        else{
            for(PolicyHeart heart : heartList){
                for (PolicyResponseDto dto : list){
                    if(heart.getPolicy().getNo() == dto.getNo()){
                        dto.setIsLiked("Y");
                    }
                }
            }

            return list;
        }
    }

    /**
     * 현재 로그인한 유저가 해당 정책을 관심 정책에 추가했는지 검사
     * @param memberDto 현재 로그인한 유저
     * @param policyNo 정책 번호
     */
    public boolean hasLiked(MemberDto memberDto, int policyNo) {

        PolicyHeart policyHeart = policyHeartRepository.findByMemberNoAndPolicyNo(memberDto.getNo(), policyNo);
        return policyHeart == null;
    }

    /**
     * 현재 로그인한 유저의 관심 정책으로 등록
     * @param memberDto 현재 로그인한 유저
     * @param policyNo 정책 번호
     */
    public void addPolicyHeart(MemberDto memberDto, int policyNo){

        Policy policy = policyRepository.findByNo(policyNo);
        Member member = memberRepository.findByNo(memberDto.getNo());

        policyHeartRepository.save(PolicyHeart.builder()
                .member(member)
                .policy(policy)
                .build());
    }

    /**
     * 현재 로그인한 유저의 관심 정책에서 삭제
     * @param memberDto 현재 로그인한 유저
     * @param policyNo 정책 번호
     */
    public void deletePolicyHeart(MemberDto memberDto, int policyNo){

        PolicyHeart policyHeart = policyHeartRepository.findByMemberNoAndPolicyNo(memberDto.getNo(), policyNo);
        policyHeartRepository.deleteById(policyHeart.getNo());
    }
}
