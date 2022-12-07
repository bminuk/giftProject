package com.gift.repository.exchange;

import com.gift.dto.exchange.ExchangeImgDto;
import com.gift.entity.contest.ContestImg;
import com.gift.entity.exchange.ExchangeImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExchangeImgRepository extends JpaRepository<ExchangeImg, Long> {

    List<ContestImg> findByExchangeIdOrderByIdAsc(Long exchangeId);

    ExchangeImgDto findByExchangeId(Long exchangeId);
    @Transactional
    void deleteByExchangeId(Long exchangeId);
}
