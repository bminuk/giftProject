package com.gift.service.contest;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.contest.ContestDto;
import com.gift.dto.contest.MainContestDto;
import com.gift.dto.exchange.MainExchangeDto;
import com.gift.dto.search.SearchDto;
import com.gift.dto.sell.MainSellDto;
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
    public Page<MainContestDto> getMainContestPage(Pageable pageable) {
        return contestRepository.getMainContestPage(pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainContestDto> getSearchContestPage(SearchDto searchDto, Pageable pageable) {
        return contestRepository.getSearchContestPage(searchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainContestDto> getMemberContestPage(Long id, Pageable pageable) {
        return contestRepository.getMemberContestPage(id, pageable);
    }
}
