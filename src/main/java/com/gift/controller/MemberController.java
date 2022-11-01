package com.gift.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gift.auth.PrincipalDetails;
import com.gift.dto.MemberDto;
import com.gift.entity.Member;
import com.gift.model.KakaoProfile;
import com.gift.model.OAuthToken;
import com.gift.repository.MemberRepository;
import com.gift.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.UUID;


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
//    @PostMapping(value = "/new")
//    public String memberForm(MemberDto memberFormDto){
//        Member member = Member.createMember(memberFormDto, passwordEncoder);
//        memberService.saveMember(member);
//
//        return "redirect:/";
//    }

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

    //잘좀해봐 신민영
    @GetMapping(value = "/callback") 
    public @ResponseBody UserDetails kakaoCallback(String code) { //response body를 붙인 건 데이터를 리턴해주는 컨트롤러 함수가 됨
        //POST방식으로 key=value 데이터를 요청(카카오쪽으로)
        //a태그를 이용한 전달방식은 무조건 get방식
        //Retrofit2
        //OkHttp
        //RestTemplate
        RestTemplate rt = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //body부분
        //아래처럼 쓰는 것이 아니라 변수로 만들어 add 하는 것이 더 좋은 방법
        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "49366b42c85f5715fc1c150d5082ded5");
        params.add("redirect_url", "http://localhost:8081/members/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트로 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        //Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //Gson, Json Simple, ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());

        RestTemplate rt2 = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpHeader와 HttpBody를 하나의 오브젝트로 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);

        //Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //Member 오브젝트 : username, password, email
        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        System.out.println("임시 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("임시 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        UUID garbagePassword = UUID.randomUUID();
        System.out.println("임시 패스워드 : " + garbagePassword);

        Member member = Member.kakaoMember(kakaoProfile.properties.getNickname(),
                kakaoProfile.getKakao_account().getEmail(),
                garbagePassword.toString());
        //db에 넣고 회원가입이 폼 길이수정 혹은 공백제한 수정하기

        Member findMember = memberRepository.findByEmail(kakaoProfile.getKakao_account().getEmail());
        if(findMember==null){

        memberService.saveMember(member);
        return new PrincipalDetails(member);
        }

        return new PrincipalDetails(member);
    }

}
