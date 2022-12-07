package com.gift.repository.request;

import com.gift.dto.request.RequestImgDto;
import com.gift.entity.request.RequestImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestImgRepository extends JpaRepository<RequestImg, Long> {

    List<RequestImg> findByRequestIdOrderByIdAsc(Long requestId);

    RequestImgDto findByRequestId(Long requestId);
    @Transactional
    void deleteByRequestId(Long requestId);
}
