package com.gift.dto;

import com.gift.entity.RequestImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;

@Getter @Setter
public class RequestImgDto {

    private Long id;

    private String requestImgName;

    private String requestOriImgName;

    private String requestImgUrl;

    private String requestRepImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static RequestImgDto of(RequestImg requestImg) {
        return modelMapper.map(requestImg, RequestImgDto.class);
    }
}
