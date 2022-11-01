package com.gift.repository;

import com.gift.entity.Member;
import com.gift.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long> {

}
