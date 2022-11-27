package com.gift.dto.search;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchDto {

    private String searchBy;

    private String searchQuery = "";
}
