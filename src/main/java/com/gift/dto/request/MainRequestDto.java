package com.gift.dto.request;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainRequestDto {

    private Long id;

    private String requestTitle;

    private String requestImgUrl;

    @QueryProjection
    public MainRequestDto(Long id, String requestTitle, String requestImgUrl) {
        this.id = id;
        this.requestTitle = requestTitle;
        this.requestImgUrl = requestImgUrl;
    }

}
