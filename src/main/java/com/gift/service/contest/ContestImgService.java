package com.gift.service.contest;

import com.gift.entity.contest.ContestImg;
import com.gift.entity.request.RequestImg;
import com.gift.repository.contest.ContestImgRepository;
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
public class ContestImgService {

    @Value("${contestImgLocation}")
    private String contestImgLocation;

    private final ContestImgRepository contestImgRepository;

    private final FileService fileService;

    public void saveContestImg(ContestImg contestImg, MultipartFile contestImgFile) throws Exception {
        String oriImgName = contestImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(contestImgLocation, oriImgName, contestImgFile.getBytes());
            imgUrl = "/projectimg/contest/" + imgName;
        }

        //이미지 정보 저장
        contestImg.updateRequestImg(oriImgName, imgName, imgUrl);
        contestImgRepository.save(contestImg);
    }
}
