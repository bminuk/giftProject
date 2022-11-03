package com.gift.entity.member;

import com.gift.constant.Role;
import com.gift.dto.member.MemberDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
@ToString
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Column(unique = true, name = "member_email")
    private String email;

    @Column(name = "member_pw")
    private String password;

    @Column(name = "member_role")
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

//    public static Member kakaoMember(String name, String email, String k_password) {
//        Member member = new Member();
//        PasswordEncoder passwordEncoder = new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return null;
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return false;
//            }
//        };
//        member.setName(name);
//        String password = passwordEncoder.encode(k_password);
//        member.setPassword(password);
//        member.setEmail(email);
//        member.setRole(Role.USER);
//
//        return member;
//    }



}
