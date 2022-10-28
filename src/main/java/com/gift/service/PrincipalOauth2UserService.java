package com.gift.service;

import com.gift.auth.PrincipalDetails;
import com.gift.constant.Role;
import com.gift.dto.MemberDto;
import com.gift.entity.Member;
import com.gift.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {



    @Autowired
    private MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : "+userRequest.getClientRegistration());
        System.out.println("userRequest : "+super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttribute("name").toString());
        //회원가입 진행
        MemberDto memberDto = new MemberDto();
        memberDto.setName(oAuth2User.getAttribute("name").toString());
        memberDto.setPassword(oAuth2User.getAttribute("sub"));
        memberDto.setEmail(oAuth2User.getAttribute("email"));


        String provider = userRequest.getClientRegistration().getClientId();
        String providerId =oAuth2User.getAttribute("sub");
        String name = provider+"_"+providerId; //google_00000
        String password = providerId;
        String email = oAuth2User.getAttribute("email");
        String role = Role.USER.toString();
        System.out.println("여기서 되나요??");


        Member findMember = memberRepository.findByEmail(email);
        if(findMember==null){
            memberRepository.save(Member.createMember(memberDto, new BCryptPasswordEncoder()));
            System.out.println("새로 회원가입한 회원입니다.");

            Member newMember = memberRepository.findByEmail(memberDto.getEmail());
            return new PrincipalDetails(newMember,oAuth2User.getAttributes());

        }else {
            System.out.println("이미 가입된 회원입니다.");
            return new PrincipalDetails(findMember, oAuth2User.getAttributes());
        }
    }
}
