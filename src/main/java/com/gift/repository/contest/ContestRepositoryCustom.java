package com.gift.repository.contest;

import com.gift.dto.contest.MainContestDto;
import com.gift.dto.request.MainRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContestRepositoryCustom {
    Page<MainContestDto> getMainContestPage(Pageable pageable);
}
