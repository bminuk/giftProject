package com.gift.service.contest;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.contest.ContestDto;
import com.gift.dto.contest.ContestImgDto;
import com.gift.dto.contest.MainContestDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.entity.contest.Contest;
import com.gift.entity.contest.ContestImg;
import com.gift.entity.member.Member;
import com.gift.entity.sell.SellImg;
import com.gift.repository.contest.ContestImgRepository;
import com.gift.repository.contest.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;

    private final ContestImgService contestImgService;
    private final ContestImgRepository contestImgRepository;

    public Long saveContest(ContestDto contestDto, List<MultipartFile> contestImgFileList, Authentication authentication) throws Exception{
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member member = principalDetails.getUser();

        Contest contest = contestDto.createContest();
        contest.setMember(member);
        contestRepository.save(contest);

        for(int i=0; i<contestImgFileList.size(); i++) {
            ContestImg contestImg = new ContestImg();
            contestImg.setContest(contest);
            if(i==0) {
                contestImg.setContestRepImgYn("Y");
            } else {
                contestImg.setContestRepImgYn("N");
            }

            contestImgService.saveContestImg(contestImg, contestImgFileList.get(i));

        }

        return contest.getId();
    }

    @Transactional(readOnly = true)
    public ContestDto getContestDt1(Long contestId) {
        //이미지 조회
        List<ContestImg> contestImgList = contestImgRepository.findByContestIdOrderByIdAsc(contestId);
        List<ContestImgDto> contestImgDtoList = new ArrayList<>();
        //조회한 이미지 엔티티를 dto 객체로 만들어 리스트에 추가
        for(ContestImg contestImg : contestImgList) {
            ContestImgDto contestImgDto = ContestImgDto.of(contestImg);
            contestImgDtoList.add(contestImgDto);
        }
        //아이디 통해 엔티티 조회 후 예외 발생
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(EntityNotFoundException::new);
        ContestDto contestDto = ContestDto.of(contest);
        contestDto.setContestImgDtoList(contestImgDtoList);
        return contestDto;


    }

    public Long updateContest(ContestDto contestDto, List<MultipartFile> contestImgFileList) throws Exception {
        //화면으로부터 전달받은 아이디 이용해 상품 엔티티 조회
        Contest contest = contestRepository.findById(contestDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        //상품 엔티티 업데이트
        contest.updateContest(contestDto);
        //이미지 아이디 리스트 조회
        List<Long> contestImgIds = contestDto.getContestImgIds();
        for(int i=0; i<contestImgFileList.size();i++) {
            //이미지 업데이트 위해 메소드에 파라미터 전달
            contestImgService.updateContestImg(contestImgIds.get(i), contestImgFileList.get(i));
        }
        return contest.getId();
    }

    @Transactional(readOnly = true)
    public Page<MainContestDto> getMainContestPage(Pageable pageable) {
        return contestRepository.getMainContestPage(pageable);
    }
}
