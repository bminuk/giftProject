package com.gift.repository.sell;

import com.gift.entity.sell.Sell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellRepository extends JpaRepository<Sell, Long> {

    Page<Sell> findAll(Pageable pageable);

    Optional<Sell> findById(Long id);

}
