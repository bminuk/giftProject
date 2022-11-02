package com.gift.repository;

import com.gift.entity.Contest;
import com.gift.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContestRepository extends JpaRepository<Contest, Long> {

}
