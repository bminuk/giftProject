package com.gift.service.request;

import com.gift.entity.exchange.ExchangeImg;
import com.gift.entity.request.RequestImg;
import com.gift.repository.request.RequestImgRepository;
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
public class RequestImgService {

    @Value("${requestImgLocation}")
    private String requestImgLocation;

    private final RequestImgRepository requestImgRepository;

    private final FileService fileService;

    public void saveRequestImg(RequestImg requestImg, MultipartFile requestImgFile) throws Exception {
        String oriImgName = requestImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(requestImgLocation, oriImgName, requestImgFile.getBytes());
            imgUrl = "/projectimg/request/" + imgName;
        }

        //이미지 정보 저장
        requestImg.updateRequestImg(oriImgName, imgName, imgUrl);
        requestImgRepository.save(requestImg);
    }

    public void updateExchangeImg(Long id, MultipartFile multipartFile) throws Exception {

        if (!multipartFile.isEmpty()) {
            RequestImg savedRequestImg = requestImgRepository.findById(id).orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if (!StringUtils.isEmpty(savedRequestImg.getRequestImgName())) {
                fileService.deleteFile(requestImgLocation + "/" +
                        savedRequestImg.getRequestImgName());
            }

            String oriImgName = multipartFile.getOriginalFilename();
            String imgName = fileService.uploadFile(requestImgLocation, oriImgName, multipartFile.getBytes());
            String imgUrl = "/projectimg/request/" + imgName;
            savedRequestImg.updateRequestImg(oriImgName, imgName, imgUrl);
        }
    }

}
