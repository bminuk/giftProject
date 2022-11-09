package com.gift.service.exchange;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.exchange.ExchangeDto;
import com.gift.entity.exchange.Exchange;
import com.gift.entity.exchange.ExchangeImg;
import com.gift.entity.member.Member;
import com.gift.repository.exchange.ExchangeImgRepository;
import com.gift.repository.exchange.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.Pipe;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    private final ExchangeImgService exchangeImgService;

    private final ExchangeImgRepository exchangeImgRepository;

    public Long saveExchange(ExchangeDto exchangeDto, List<MultipartFile> exchangeImgFileList, Authentication authentication) throws Exception {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Member member = principalDetails.getUser();
        Exchange exchange = exchangeDto.createExchange();
        exchange.setMember(member);
        exchangeRepository.save(exchange);

        for(int i=0; i<exchangeImgFileList.size(); i++) {
            ExchangeImg exchangeImg = new ExchangeImg();
            exchangeImg.setExchange(exchange);
            if(i==0) {
                exchangeImg.setExchangeRepImgYn("Y");
            } else {
                exchangeImg.setExchangeRepImgYn("N");
            }
            exchangeImgService.saveExchangeImg(exchangeImg, exchangeImgFileList.get(i));
        }
        return exchange.getId();
    }

}
