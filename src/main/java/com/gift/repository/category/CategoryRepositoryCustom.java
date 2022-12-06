package com.gift.repository.category;

import com.gift.dto.sell.MainSellDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryRepositoryCustom {
    Page<MainSellDto> getVideoPage(Pageable pageable);

    Page<MainSellDto> getItPage(Pageable pageable);

    Page<MainSellDto> getDesignPage(Pageable pageable);

    Page<MainSellDto> getLanguagePage(Pageable pageable);

    Page<MainSellDto> getHobbyPage(Pageable pageable);
}
