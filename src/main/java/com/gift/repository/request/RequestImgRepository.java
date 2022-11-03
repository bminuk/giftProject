package com.gift.repository.request;

import com.gift.entity.request.RequestImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestImgRepository extends JpaRepository<RequestImg, Long> {

    List<RequestImg> findByRequestIdOrderByIdAsc(Long requestId);
}
