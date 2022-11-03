package com.gift.service.contest;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.contest.ContestDto;
import com.gift.entity.contest.Contest;
import com.gift.entity.contest.ContestImg;
import com.gift.entity.member.Member;
import com.gift.entity.sell.SellImg;
import com.gift.repository.contest.ContestImgRepository;
import com.gift.repository.contest.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;

    private final ContestImgService contestImgService;
    private final ContestImgRepository contestImgRepository;

    public Long saveContest(ContestDto contestDto, List<MultipartFile> contestImgFileList, Authentication authentication) throws Exception{

        Contest contest = new Contest();
        contest.setTitle(contestDto.getTitle());
        contest.setField(contestDto.getField());
        contest.setPromoter(contestDto.getPromoter());
        contest.setTerm(contestDto.getTerm());
        contest.setTarget(contestDto.getTarget());
        contest.setSkill(contestDto.getSkill());

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member member = principalDetails.getUser();

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
}
