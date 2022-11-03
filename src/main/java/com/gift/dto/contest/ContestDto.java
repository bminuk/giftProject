package com.gift.dto.contest;

import com.gift.dto.sell.SellDto;
import com.gift.entity.contest.Contest;
import com.gift.entity.sell.Sell;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ContestDto {

    private String title;

    private String field;       //분야

    private String term;        //접수 기간

    private String promoter;    //주최자

    private String content;     //내용

    private String target;      //지원 가능 대상

    private String skill;       //작성자 보유 기술

    //수정할 때 이미지 정보 저장하는 리스트
    private List<ContestImgDto> contestImgDtoList = new ArrayList<>();

    private List<Long> contestImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Contest createContest() { return modelMapper.map(this, Contest.class);}

    public static ContestDto of(Contest contest) {return modelMapper.map(contest, ContestDto.class);}

}

