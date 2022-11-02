package com.gift.service;

import com.gift.dto.RequestDto;
import com.gift.entity.Request;
import com.gift.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;


    public Long saveRequest(RequestDto requestDto) throws Exception {

        Request request = requestDto.createRequest();
        requestRepository.save(request);

//        for(int i=0; i<requestImgFileList.size();i++) {
//            RequestImage requestImage = new RequestImage();
//            requestImage.setRequest(request);
//            if(i==0) {
//                requestImage.setRequest_image_yn("Y");
//            } else {
//                requestImage.setRequest_image_yn("N");
//            }
//            requestImageService.saveRequestImage(requestImage, requestImgFileList.get(i));
//        }
        return request.getId();
    }
}
