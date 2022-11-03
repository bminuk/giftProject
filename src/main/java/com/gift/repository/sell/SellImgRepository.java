package com.gift.repository.sell;

import com.gift.entity.request.RequestImg;
import com.gift.entity.sell.SellImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellImgRepository extends JpaRepository<SellImg, Long> {

    List<SellImg> findBySellIdOrderByIdAsc(Long sellId);
}
