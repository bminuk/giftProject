package com.gift.repository.sell;

import com.gift.dto.search.SearchDto;
import com.gift.dto.sell.MainSellDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellRepositoryCustom {

    Page<MainSellDto> getMainSellPage(Pageable pageable);

    Page<MainSellDto> getSearchSellPage(SearchDto searchDto, Pageable pageable);
}
