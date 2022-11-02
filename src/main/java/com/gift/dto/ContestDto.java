package com.gift.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
public class ContestDto {

    private String title;

    private String field;       //분야

    private String term;        //접수 기간

    private String promoter;    //주최자

    private String content;     //내용

    private String target;      //지원 가능 대상

    private String skill;       //작성자 보유 기술
}
