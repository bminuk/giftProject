package com.gift.dto.contest;

import com.gift.dto.request.RequestImgDto;
import com.gift.entity.contest.ContestImg;
import com.gift.entity.request.RequestImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ContestImgDto {

    private String title;

    private String field;       //분야

    private String term;        //접수 기간

    private String promoter;    //주최자

    private String content;     //내용

    private String target;      //지원 가능 대상

    private String skill;       //작성자 보유 기술

    private static ModelMapper modelMapper = new ModelMapper();

    public static ContestImgDto of(ContestImg contestImg) {
        return modelMapper.map(contestImg, ContestImgDto.class);
    }
}
