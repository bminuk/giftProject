package com.gift.repository.exchange;

import com.gift.entity.contest.Contest;
import com.gift.entity.exchange.Exchange;
import com.gift.repository.contest.ContestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ExchangeRepository extends JpaRepository<Exchange, Long>, QuerydslPredicateExecutor<Exchange>, ExchangeRepositoryCustom{
}
