package com.gift.dto.contest;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainContestDto {

    private Long id;

    private String contestTitle;

    private String contestImgUrl;

    @QueryProjection
    public MainContestDto(Long id, String contestTitle, String contestImgUrl) {
        this.id = id;
        this.contestTitle = contestTitle;
        this.contestImgUrl = contestImgUrl;
    }

}
