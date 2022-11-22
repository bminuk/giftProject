package com.gift.service.exchange;

import com.gift.entity.exchange.ExchangeImg;
import com.gift.repository.exchange.ExchangeImgRepository;
import com.gift.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

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

}
