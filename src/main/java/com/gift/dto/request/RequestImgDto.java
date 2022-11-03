package com.gift.dto.request;

import com.gift.entity.request.RequestImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

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
