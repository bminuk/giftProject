package com.gift.service;

import com.gift.dto.ContestDto;
import com.gift.entity.Contest;
import com.gift.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;

    public Contest saveContest(ContestDto contestDto){

        Contest contest = new Contest();
        contest.setTitle(contestDto.getTitle());
        contest.setField(contestDto.getField());
        contest.setPromoter(contestDto.getPromoter());
        contest.setTerm(contestDto.getTerm());
        contest.setTarget(contestDto.getTarget());
        contest.setSkill(contestDto.getSkill());

        return contestRepository.save(contest);
    }
}
