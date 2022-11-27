package com.gift.dto.member;

import com.gift.entity.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;//백엔드에서 벨리데이션을 설정
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 최소 8자이상, 최대 16자이하로 입력해주세요.")
    private String password;

    private static ModelMapper modelMapper = new ModelMapper();

    public Member createMember() {
        return modelMapper.map(this, Member.class);}

    public static MemberDto of(Member member) {
        return modelMapper.map(member, MemberDto.class);}

}
