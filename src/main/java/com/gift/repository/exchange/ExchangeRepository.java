package com.gift.repository.exchange;

import com.gift.entity.contest.Contest;
import com.gift.entity.exchange.Exchange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, Long>, QuerydslPredicateExecutor<Exchange>, ExchangeRepositoryCustom {

    Page<Exchange> findAll(Pageable pageable);

    Optional<Exchange> findById(Long id);
}
