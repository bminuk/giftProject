package com.gift.dto.sell;

import com.gift.dto.request.RequestImgDto;
import com.gift.entity.request.RequestImg;
import com.gift.entity.sell.SellImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class SellImgDto {

    private Long id;

    private String sellImgName;

    private String sellOriImgName;

    private String sellImgUrl;

    private String sellRepImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static SellImgDto of(SellImg sellImg) {
        return modelMapper.map(sellImg, SellImgDto.class);
    }
}
