package com.gift.entity.exchange;

import com.gift.entity.contest.ContestImg;
import com.gift.entity.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "exchange")
    private List<ExchangeImg> exchangeImg = new ArrayList<>() ;

}
