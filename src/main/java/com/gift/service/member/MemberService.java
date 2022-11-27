package com.gift.service.member;

//import com.gift.auth.PrincipalDetails;
import com.gift.auth.PrincipalDetails;
import com.gift.dto.member.MemberDto;
import com.gift.entity.member.Member;
import com.gift.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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


        return new PrincipalDetails(member);
    }

    @Transactional(readOnly = true)
    public MemberDto getMemberDto(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member member = principalDetails.getUser();
        long id = member.getId();
        Member accessMember = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        MemberDto memberDto = MemberDto.of(accessMember);
        return memberDto;
    }
}
