package com.gift.dto.sell;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainSellDto {

    private Long id;

    private String sellTitle;

    private String sellImgUrl;

    @QueryProjection
    public MainSellDto(Long id, String sellTitle, String sellImgUrl) {
        this.id = id;
        this.sellTitle = sellTitle;
        this.sellImgUrl = sellImgUrl;
    }
}
