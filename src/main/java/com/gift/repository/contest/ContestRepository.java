package com.gift.repository.contest;

import com.gift.entity.contest.Contest;
import com.gift.entity.request.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ContestRepository extends JpaRepository<Contest, Long>, QuerydslPredicateExecutor<Contest>, ContestRepositoryCustom {

    Page<Contest> findAll(Pageable pageable);

    Optional<Contest> findById(Long id);
}
