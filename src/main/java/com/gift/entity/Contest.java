package com.gift.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "contest")
@Getter
@Setter
@ToString
public class Contest {

    @Id
    @Column(name = "contest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "contest_title")
    private String title;

    @Column(name= "contest_field")
    private String field;       //분야

    @Column(name= "contest_term")
    private String term;       //접수 기간 나중에 Sting 말고 Date 같은걸로 받아야 할듯? 입력폼도 그거에 맞게 설정해줘야 할 듯

    @Column(name= "contest_promoter")
    private String promoter;    //주최자

    @Column(name= "contest_content")
    private String content;     //내용

    @Column(name= "contest_target")
    private String target;      //지원 가능 대상

    @Column(name= "contest_skill")
    private String skill;       //작성자 보유 기술

}
