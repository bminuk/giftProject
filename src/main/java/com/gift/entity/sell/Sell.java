package com.gift.entity.sell;

import com.gift.constant.Category;
import com.gift.entity.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "sell")
@Getter @Setter
@ToString
public class Sell {

    @Id
    @Column(name = "sell_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
//     추후 포트 폴리오 엔티티 생성 후 만들어야 할 듯?
//    @JoinColumn(name = "portfolio_id")
//    private Portfolio portfolio;
    @Column(name= "sell_title")
    private String title;

    @Column(name = "sell_content")
    private String content;

    @Column(name = "sell_money")
    private int money;

    @Enumerated(EnumType.STRING)
    @Column(name = "sell_category")
    private Category sellCategory;

    }





