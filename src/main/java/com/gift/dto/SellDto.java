package com.gift.dto;

import com.gift.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter
public class SellDto {


    private Member member;
//     추후 포트 폴리오 엔티티 생성 후 만들어야 할 듯?
//    private Portfolio portfolio;
    private String title;

    private String content;

    private int money;





}
