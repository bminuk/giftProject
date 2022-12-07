package com.gift.entity.contest;

import com.gift.constant.Category;
import com.gift.dto.contest.ContestDto;
import com.gift.dto.sell.SellDto;
import com.gift.entity.member.Member;
import com.gift.entity.sell.SellImg;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name= "contest_term")
    private String term;       //접수 기간 나중에 Sting 말고 Date 같은걸로 받아야 할듯? 입력폼도 그거에 맞게 설정해줘야 할 듯

    @Column(name= "contest_promoter")
    private String promoter;    //주최자

    @Column(name= "contest_target")
    private String target;      //지원 가능 대상

    @Column(name= "contest_skill")
    private String skill;       //작성자 보유 기술

    @Enumerated(EnumType.STRING)
    @Column(name = "contest_category")
    private Category contestCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "contest")
    private List<ContestImg> contestImg = new ArrayList<>() ;

    public void updateContest(ContestDto contestDto) {
        this.title = contestDto.getTitle();
        this.term = contestDto.getTerm();
        this.promoter = contestDto.getPromoter();
        this.skill = contestDto.getSkill();
        this.contestCategory = contestDto.getContestCategory();
    }

}
