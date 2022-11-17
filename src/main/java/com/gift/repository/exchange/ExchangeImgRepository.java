package com.gift.repository.exchange;

import com.gift.entity.contest.ContestImg;
import com.gift.entity.exchange.ExchangeImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeImgRepository extends JpaRepository<ExchangeImg, Long> {

    List<ExchangeImg> findByExchangeIdOrderByIdAsc(Long exchangeId);
}
