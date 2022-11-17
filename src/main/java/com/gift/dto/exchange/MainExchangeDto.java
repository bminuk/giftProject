package com.gift.dto.exchange;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainExchangeDto {

    private Long id;

    private String exchangeTitle;

    private String exchangeImgUrl;

    @QueryProjection
    public MainExchangeDto(Long id, String exchangeTitle, String exchangeImgUrl) {
        this.id = id;
        this.exchangeTitle = exchangeTitle;
        this.exchangeImgUrl = exchangeImgUrl;
    }

}
