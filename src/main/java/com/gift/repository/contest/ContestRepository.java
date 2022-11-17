package com.gift.repository.contest;

import com.gift.entity.contest.Contest;
import com.gift.entity.request.Request;
import com.gift.repository.request.RequestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ContestRepository extends JpaRepository<Contest, Long>, QuerydslPredicateExecutor<Contest>, ContestRepositoryCustom {

}