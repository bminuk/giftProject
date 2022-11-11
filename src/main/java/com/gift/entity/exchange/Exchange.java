package com.gift.entity.exchange;

import com.gift.constant.Category;
import com.gift.entity.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="exchange")
@Getter
@Setter
@ToString
public class Exchange {

    @Id
    @Column(name="exchange_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "exchange_title")
    private String exchangeTitle;

    @Column(nullable = false, name = "exchange_intro")
    private String exchangeIntro;

    @Column(nullable = false, name = "exchange_date")
    private String exchangeDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "exchange_category")
    private Category exchangeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
