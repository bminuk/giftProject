package com.gift.dto.exchange;

import com.gift.dto.request.RequestImgDto;
import com.gift.entity.exchange.ExchangeImg;
import com.gift.entity.request.RequestImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ExchangeImgDto {


    private Long id;

    private String exchangeImgName;

    private String exchangeOriImgName;

    private String exchangeImgUrl;

    private String exchangeRepImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ExchangeImgDto of(ExchangeImg exchangeImg) {
        return modelMapper.map(exchangeImg, ExchangeImgDto.class);
    }
}
