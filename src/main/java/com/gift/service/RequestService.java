package com.gift.service;

import com.gift.dto.RequestDto;
import com.gift.entity.Request;
import com.gift.entity.RequestImg;
import com.gift.repository.RequestImgRepository;
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
    private final RequestImgService requestImgService;
    private final RequestImgRepository requestImgRepository;


    public Long saveRequest(RequestDto requestDto, List<MultipartFile> requestImgFileList) throws Exception {

        Request request = requestDto.createRequest();
        requestRepository.save(request);

        for(int i=0; i<requestImgFileList.size();i++) {
            RequestImg requestImg = new RequestImg();
            requestImg.setRequest(request);
            if(i==0) {
                requestImg.setRequestRepImgYn("Y");
            } else {
                requestImg.setRequestRepImgYn("N");
            }
            requestImgService.saveRequestImg(requestImg, requestImgFileList.get(i));
        }
        return request.getId();
    }
}
