package com.gift.repository.sell;

import com.gift.dto.sell.SellImgDto;
import com.gift.entity.request.RequestImg;
import com.gift.entity.sell.SellImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SellImgRepository extends JpaRepository<SellImg, Long> {

    List<SellImg> findBySellIdOrderByIdAsc(Long sellId);

    SellImgDto findBySellId(Long sellId);
    @Transactional
    void deleteBySellId(Long sellId);
}
