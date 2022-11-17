package com.gift.service.exchange;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.exchange.ExchangeDto;
import com.gift.dto.exchange.ExchangeImgDto;
import com.gift.dto.exchange.MainExchangeDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.dto.request.RequestDto;
import com.gift.dto.request.RequestImgDto;
import com.gift.entity.exchange.Exchange;
import com.gift.entity.exchange.ExchangeImg;
import com.gift.entity.member.Member;
import com.gift.entity.request.Request;
import com.gift.entity.request.RequestImg;
import com.gift.repository.exchange.ExchangeImgRepository;
import com.gift.repository.exchange.ExchangeRepository;
import com.gift.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExchangeService {

    private final ExchangeRepository exchangeRepository;

    private final ExchangeImgService exchangeImgService;

    private final ExchangeImgRepository exchangeImgRepository;

    private final FileService fileService;

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

    @Transactional(readOnly = true)
    public ExchangeDto getExchangeDt1(Long exchangeId) {
        List<ExchangeImg> exchangeImgList = exchangeImgRepository.findByExchangeIdOrderByIdAsc(exchangeId);
        List<ExchangeImgDto> exchangeImgDtoList = new ArrayList<>();
        for(ExchangeImg exchangeImg : exchangeImgList) {
            ExchangeImgDto exchangeImgDto = ExchangeImgDto.of(exchangeImg);
            exchangeImgDtoList.add(exchangeImgDto);
        }

        Exchange exchange = exchangeRepository.findById(exchangeId)
                .orElseThrow(EntityNotFoundException::new);
        ExchangeDto exchangeDto = ExchangeDto.of(exchange);
        exchangeDto.setExchangeImgDtoList(exchangeImgDtoList);
        return exchangeDto;
    }

    public Long updateExchange(ExchangeDto exchangeDto, List<MultipartFile> exchangeImgFileList) throws Exception {

        Exchange exchange = exchangeRepository.findById(exchangeDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        exchange.updateExchange(exchangeDto);

        List<Long> exchangeImgIds = exchangeDto.getExchangeImgIds();

        for(int i=0; i<exchangeImgFileList.size(); i++) {
            exchangeImgService.updateExchangeImg(exchangeImgIds.get(i), exchangeImgFileList.get(i));
        }
        return exchange.getId();
    }

    @Transactional(readOnly = true)
    public Page<MainExchangeDto> getMainExchangePage(Pageable pageable) {
        return exchangeRepository.getMainExchangePage(pageable);
    }
}
