package com.gift.entity;

import com.gift.constant.Role;
import com.gift.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "member")

public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    //멤버 엔티티 생성 메소드, 회원을 생성하는 메소드를 만들어서 관리하면 코드가 변경되더라고 한군데만 수정하면 된다.
    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());
        String password = passwordEncoder.encode(memberDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.USER);
        return member;

    }



}
