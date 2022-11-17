package com.gift.repository.request;

import com.gift.dto.request.MainRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequestRepositoryCustom {

//    Page<Request> getAdminRequestPage(RequestSearchDto requestSearchDto, Pageable pageable);

    Page<MainRequestDto> getMainRequestPage(Pageable pageable);
}
