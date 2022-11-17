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

    public void updateContestImg(Long contestImgId, MultipartFile contestImgFile) throws Exception{
        if(!contestImgFile.isEmpty()) {
            //이미지 아이디 이용해 기존 저장했던 이미지 엔티티 조회
            ContestImg savedContestImg = contestImgRepository.findById(contestImgId)
                    .orElseThrow(EntityNotFoundException::new);
            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedContestImg.getContestImgName())) {
                fileService.deleteFile((contestImgLocation+"/"+savedContestImg.getContestImgName()));
            }
            String oriImgName = contestImgFile.getOriginalFilename();
            //업데이트한 이미지 파일 업로드
            String imgName = fileService.uploadFile(contestImgLocation, oriImgName, contestImgFile.getBytes());
            String imgUrl = "/projectimg/contest/" + imgName;
            //첫 업로드때처럼 레포지토리에 save하지 않음
            //엔티티가 영속 상태라 데이터를 변경하는 것만으로 변경 감지 기능이 동작해 트랜잭션이 끝날 때 update 쿼리 실행
            savedContestImg.updateContestImg(oriImgName, imgName,imgUrl);
        }
    }
}
