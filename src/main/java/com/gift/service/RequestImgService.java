package com.gift.service;

import com.gift.entity.RequestImg;
import com.gift.repository.RequestImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

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
            imgUrl = "/requestimg/request/" + imgName;
        }

        //이미지 정보 저장
        requestImg.updateRequestImg(oriImgName, imgName, imgUrl);
        requestImgRepository.save(requestImg);
    }
}
