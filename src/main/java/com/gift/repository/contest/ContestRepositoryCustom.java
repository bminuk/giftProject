package com.gift.repository.contest;

import com.gift.dto.contest.MainContestDto;
import com.gift.dto.exchange.MainExchangeDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.dto.search.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContestRepositoryCustom {
    Page<MainContestDto> getMainContestPage(Pageable pageable);

    Page<MainContestDto> getSearchContestPage(SearchDto searchDto, Pageable pageable);

    Page<MainContestDto> getMemberContestPage(Long id, Pageable pageable);
}
