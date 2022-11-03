package com.gift.service.request;

import com.gift.entity.request.RequestImg;
import com.gift.repository.request.RequestImgRepository;
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
}
