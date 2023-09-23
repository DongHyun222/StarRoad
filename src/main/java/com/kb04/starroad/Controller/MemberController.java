package com.kb04.starroad.Controller;


import com.kb04.starroad.Dto.MemberDto;
import com.kb04.starroad.Entity.Member;
import com.kb04.starroad.Service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Api(tags={"회원 API"})
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "회원가입 폼 조회", notes = "회원가입 폼을 조회할 수 있다")
    @GetMapping("/starroad/member")
    public ModelAndView member() {
        ModelAndView mav = new ModelAndView("member/member");
        return mav;
    }

    @ApiOperation(value = "회원가입", notes = "회원가입을 할 수 있다")
    @PostMapping("/starroad/member")
    public ModelAndView member(
            @ApiParam(value = "회원 정보")
            @RequestBody @ModelAttribute MemberDto dto) {
        ModelAndView mav = new ModelAndView("redirect:/starroad/login");

        dto.setStatus('Y');
        dto.setAgreement('Y');
        String num = dto.getPhone().replace(",", "-");
        dto.setPhone(num);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encPass = encoder.encode(dto.getPassword());
        dto.setPassword(encPass);

        memberService.memberInsert(dto);

        return mav;
    }

    // 아이디 중복 확인을 위한 엔드포인트
    @ApiOperation(value = "회원아이디 중복체크", notes = "회원아이디 중복체크 할 수 있다")
    @PostMapping("/starroad/checkMemberId")
    public String checkId(
            @ApiParam(value = "회원아이디")
            @RequestParam("id") String id) {
        Optional<Member> optionalMember = memberService.checkId(id);
        return optionalMember.isPresent() ? "Y" : "N";
    }

    @ApiOperation(value = "회원이메일 중복체크", notes = "회원이메일 중복체크 할 수 있다")
    @PostMapping("/starroad/checkMemberEmail")
    public String checkEmail(
            @ApiParam(value = "회원이메일")
            @RequestParam("email") String email) {
        String result = "Y";
        Member optionalMember = memberService.checkEmail(email);
        if (optionalMember == null) {
            // 아이디가 존재하지 않는 경우
            result = "N";
        }
        //아이디가 있을시 Y 없을시 N으로 jsp view 로 보냄
        return result;
    }

}