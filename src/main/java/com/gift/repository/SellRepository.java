package com.gift.repository;

import com.gift.entity.Member;
import com.gift.entity.Sell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellRepository extends JpaRepository<Sell, Long> {

    Page<Sell> findAll(Pageable pageable);

}
