package com.gift.repository.exchange;

import com.gift.entity.exchange.Exchange;
import com.gift.entity.request.Request;
import com.gift.repository.request.RequestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ExchangeRepository extends JpaRepository<Exchange, Long>, QuerydslPredicateExecutor<Exchange>, ExchangeRepositoryCustom {
}
