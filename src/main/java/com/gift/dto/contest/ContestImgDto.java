package com.gift.dto.contest;

import com.gift.dto.request.RequestImgDto;
import com.gift.entity.contest.ContestImg;
import com.gift.entity.request.RequestImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ContestImgDto {

    private Long id;


    private String contesttImgName;

    private String contestOriImgName;

    private String conteststImgUrl;

    private String contestRepImgYn;


    private static ModelMapper modelMapper = new ModelMapper();

    public static ContestImgDto of(ContestImg contestImg) {
        return modelMapper.map(contestImg, ContestImgDto.class);
    }
}
