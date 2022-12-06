package com.gift.controller;

import com.gift.dto.member.MemberDto;
import com.gift.entity.member.Member;
//import com.gift.model.KakaoProfile;
//import com.gift.model.OAuthToken;
import com.gift.repository.member.MemberRepository;
import com.gift.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;


@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(value = "/join")
    public String memberForm(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/memberJoin";
    }

    @PostMapping(value = "/join")
    public String newMember(@Valid MemberDto memberDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/memberJoin";
        }
        try{
            Member member = Member.createMember(memberDto, passwordEncoder);
            memberService.saveMember(member);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "member/memberJoin";
        }

        return "redirect:/";
    }


    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLogin";
    }

    @GetMapping(value = "/idFind")
    public String idFind(){
        return "/member/memberIdFind";
    }

    @GetMapping(value = "/pwFind")
    public String pwFind(){
        return "/member/memberPwFind";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLogin";
    }

    @GetMapping(value = "/myPage")
    public String memberDt(Authentication authentication, Model model) {
        try{
            MemberDto memberDto = memberService.getMemberDto(authentication);
            model.addAttribute("memberDto", memberDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "멤버 정보를 입력해주세요.");
            model.addAttribute("memberDto", new MemberDto());
            return "/member/myPage";
        }
        return "/member/myPage";
    }


}
