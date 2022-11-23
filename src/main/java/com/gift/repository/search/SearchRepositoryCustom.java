package com.gift.repository.search;

import com.gift.dto.search.SearchDto;
import com.gift.entity.request.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchRepositoryCustom {
    Page<SearchDto> getRequestPage(Pageable pageable);
}
