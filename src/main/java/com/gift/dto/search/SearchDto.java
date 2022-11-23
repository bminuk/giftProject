package com.gift.dto.search;

import com.gift.constant.Category;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchDto {

    private long id;

    private String title;

    private String imgUrl;

    private Category category;

    @QueryProjection
    public SearchDto(Long id, String title, String imgUrl, Category category) {
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
        this.category = category;
    }
}
