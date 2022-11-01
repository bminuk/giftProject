package com.gift.service;

//import com.gift.auth.PrincipalDetails;
import com.gift.auth.PrincipalDetails;
import com.gift.entity.Member;
import com.gift.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
    //implements UserDetailsService

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }
    public void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다. 카톡 로그인에서도 문제인거에요?");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member ==null){
            throw new UsernameNotFoundException(email);
        }

//        return User.builder()
//                .username(member.getEmail())
//                .password(member.getPassword())
//                .roles(member.getRole().toString())
//                .build();

        return new PrincipalDetails(member);
    }
}
