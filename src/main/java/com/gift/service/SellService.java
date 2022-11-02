package com.gift.service;

import com.gift.dto.SellDto;
import com.gift.entity.Sell;
import com.gift.repository.SellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SellService {

    private final SellRepository sellRepository;

    public Sell saveSell(SellDto sellDto){
        Sell sell = new Sell();
        sell.setContent(sellDto.getContent());
        sell.setTitle(sellDto.getTitle());
        sell.setMoney(sellDto.getMoney());

        return sellRepository.save(sell);

    }

}
