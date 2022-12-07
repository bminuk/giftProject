package com.gift.service.sell;

import com.gift.entity.sell.SellImg;
import com.gift.repository.sell.SellImgRepository;
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
public class SellImgService {

    @Value("${sellImgLocation}")
    private String sellImgLocation;

    private final SellImgRepository sellImgRepository;

    private final FileService fileService;

    public void saveSellImg(SellImg sellImg, MultipartFile sellImgFile) throws Exception {
        String oriImgName = sellImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(sellImgLocation, oriImgName, sellImgFile.getBytes());
            imgUrl = "/projectimg/sell/" + imgName;
        }

        sellImg.updateSellImg(oriImgName, imgName, imgUrl);
        sellImgRepository.save(sellImg);
    }

    public void updateSellImg(Long sellImgId, MultipartFile sellImgFile)throws Exception{

            if(!sellImgFile.isEmpty()){

                SellImg savedSellImg = sellImgRepository.findById(sellImgId).orElseThrow(EntityNotFoundException::new);


                //기존 이미지 파일 삭제
                if(!StringUtils.isEmpty(savedSellImg.getSellImgName())) {
                    fileService.deleteFile(sellImgLocation+"/"+
                            savedSellImg.getSellImgName());
                }

                String oriImgName = sellImgFile.getOriginalFilename();
                String imgName = fileService.uploadFile(sellImgLocation, oriImgName, sellImgFile.getBytes());
                String imgUrl = "/projectimg/sell/" + imgName;
                savedSellImg.updateSellImg(oriImgName, imgName, imgUrl);
            }

    }
}
