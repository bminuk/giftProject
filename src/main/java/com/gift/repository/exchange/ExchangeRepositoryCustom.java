package com.gift.repository.exchange;

import com.gift.dto.contest.MainContestDto;
import com.gift.dto.exchange.MainExchangeDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.dto.search.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExchangeRepositoryCustom {

    Page<MainExchangeDto> getMainExchangePage(Pageable pageable);

    Page<MainExchangeDto> getSearchExchangePage(SearchDto searchDto, Pageable pageable);

    Page<MainExchangeDto> getMemberExchangePage(Long id, Pageable pageable);
}
