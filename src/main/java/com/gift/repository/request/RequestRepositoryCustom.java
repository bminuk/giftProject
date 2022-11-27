package com.gift.repository.request;

import com.gift.dto.request.MainRequestDto;
import com.gift.dto.search.SearchDto;
import com.gift.dto.sell.MainSellDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequestRepositoryCustom {

//    Page<Request> getAdminRequestPage(RequestSearchDto requestSearchDto, Pageable pageable);

    Page<MainRequestDto> getMainRequestPage(Pageable pageable);

    Page<MainRequestDto> getSearchRequestPage(SearchDto searchDto, Pageable pageable);
}
