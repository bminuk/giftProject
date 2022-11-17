package com.gift.repository.exchange;

import com.gift.dto.exchange.MainExchangeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExchangeRepositoryCustom {

    Page<MainExchangeDto> getMainExchangePage(Pageable pageable);
}
