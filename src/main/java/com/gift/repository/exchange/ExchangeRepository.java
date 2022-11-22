package com.gift.repository.exchange;

import com.gift.entity.exchange.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
}
