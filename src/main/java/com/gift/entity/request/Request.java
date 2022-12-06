package com.gift.entity.request;

import com.gift.constant.Category;
import com.gift.dto.request.RequestDto;
import com.gift.entity.exchange.ExchangeImg;
import com.gift.entity.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="request")
@Getter
@Setter
@ToString
public class Request{

    @Id
    @Column(name="request_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50, name = "request_title")
    private String requestTitle;

    @Column(nullable = false, name = "request_range")
    private String requestRange;

    @Column(name = "request_date")
    private String requestDate;

    @Column(nullable = false, name = "request_content")
    private String requestContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_category")
    private Category requestCategory;

    @OneToMany(mappedBy = "request")
    private List<RequestImg> requestImg = new ArrayList<>() ;

    public void updateRequest(RequestDto requestDto) {
        this.requestTitle = requestDto.getRequestTitle();
        this.requestRange = requestDto.getRequestRange();
        this.requestDate = requestDto.getRequestDate();
        this.requestContent = requestDto.getRequestContent();
        this.requestCategory = requestDto.getRequestCategory();
    }

}
