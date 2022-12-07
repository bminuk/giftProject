package com.gift.service.contest;

import com.gift.dto.contest.ContestImgDto;
import com.gift.entity.contest.ContestImg;
import com.gift.entity.request.RequestImg;
import com.gift.entity.sell.SellImg;
import com.gift.repository.contest.ContestImgRepository;
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
        contestImg.updateContestImg(oriImgName, imgName, imgUrl);
        contestImgRepository.save(contestImg);
    }

    public void updateContestImg(Long contestImgId, MultipartFile contestImgFile)throws Exception {

        if (!contestImgFile.isEmpty()) {

            ContestImg savedContestImg = contestImgRepository.findById(contestImgId).orElseThrow(EntityNotFoundException::new);


            //기존 이미지 파일 삭제
            if (!StringUtils.isEmpty(savedContestImg.getContestImgName())) {
                fileService.deleteFile(contestImgLocation + "/" +
                        savedContestImg.getContestImgName());
            }

            String oriImgName = contestImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(contestImgLocation, oriImgName, contestImgFile.getBytes());
            String imgUrl = "/projectimg/contest/" + imgName;
            savedContestImg.updateContestImg(oriImgName, imgName, imgUrl);
        }
    }
}
