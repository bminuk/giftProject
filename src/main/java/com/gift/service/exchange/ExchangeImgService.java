package com.gift.service.exchange;

import com.gift.dto.exchange.ExchangeImgDto;
import com.gift.entity.contest.ContestImg;
import com.gift.entity.exchange.Exchange;
import com.gift.entity.exchange.ExchangeImg;
import com.gift.repository.exchange.ExchangeImgRepository;
import com.gift.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ExchangeImgService {

    @Value("${exchangeImgLocation}")
    private String exchangeImgLocation;

    private final ExchangeImgRepository exchangeImgRepository;

    private final FileService fileService;

    public void saveExchangeImg(ExchangeImg exchangeImg, MultipartFile exchangeImgFile) throws Exception {
        String oriImgName = exchangeImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(exchangeImgLocation, oriImgName, exchangeImgFile.getBytes());
            imgUrl = "/projectimg/exchange/" + imgName;
        }

        //이미지 정보 저장
        exchangeImg.updateRequestImg(oriImgName, imgName, imgUrl);
        exchangeImgRepository.save(exchangeImg);
    }

    public void updateExchangeImg(Long exchangeImgId, MultipartFile exchangeImgFile)throws Exception {

        if (!exchangeImgFile.isEmpty()) {
            ExchangeImg savedExchangeImg = exchangeImgRepository.findById(exchangeImgId).orElseThrow(EntityNotFoundException::new);


            //기존 이미지 파일 삭제
            if (!StringUtils.isEmpty(savedExchangeImg.getExchangeImgName())) {
                fileService.deleteFile(exchangeImgLocation + "/" +
                        savedExchangeImg.getExchangeImgName());
            }

            String oriImgName = exchangeImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(exchangeImgLocation, oriImgName, exchangeImgFile.getBytes());
            String imgUrl = "/projectimg/exchange/" + imgName;
            savedExchangeImg.updateRequestImg(oriImgName, imgName, imgUrl);
        }
    }
}
