package com.gift.repository.request;

import com.gift.entity.request.Request;
import com.gift.entity.sell.Sell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long>, QuerydslPredicateExecutor<Request>, RequestRepositoryCustom {

    Page<Request> findAll(Pageable pageable);

    Optional<Request> findById(Long id);
}
