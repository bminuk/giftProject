package com.gift.service;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.MemberDto;
import com.gift.entity.Member;
import com.gift.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    @Autowired
    private MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();

        String name = null;
        String email = null;
        String password = null;

        //provider가 누구인지 판별
        if (provider.equals("kakao")) {
            //카카오 로그인 부분

            Map<String, Object> attributes = oAuth2User.getAttributes();

            Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
            email = (String) kakao_account.get("email");

            Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
            name = (String) properties.get("nickname");

            password = name + email;

            System.out.println("카카오 로그인을 진행하였습니다");
            System.out.println("email : " + email);
            System.out.println("name : " + name);

        } else if (provider.equals("google")) {
            //구글 로그인 부분

            name = oAuth2User.getAttribute("name").toString();
            email = oAuth2User.getAttribute("email");
            password = oAuth2User.getAttribute("sub");

            System.out.println("구글 로그인을 진행하였습니다");
            System.out.println("email : " + email);
            System.out.println("name : " + name);

        }

        //   회원가입 진행
        MemberDto memberDto = new MemberDto();
        memberDto.setName(name);
        memberDto.setPassword(password);
        memberDto.setEmail(email);


        Member findMember = memberRepository.findByEmail(email);
        if (findMember == null) {
            memberRepository.save(Member.createMember(memberDto, new BCryptPasswordEncoder()));
            System.out.println("새로 회원가입한 회원입니다.");

            Member newMember = memberRepository.findByEmail(memberDto.getEmail());
            return new PrincipalDetails(newMember, oAuth2User.getAttributes());

        } else {
            System.out.println("이미 가입된 회원입니다.");
            return new PrincipalDetails(findMember, oAuth2User.getAttributes());
        }


    }
}
