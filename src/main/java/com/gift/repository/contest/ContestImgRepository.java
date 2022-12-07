package com.gift.repository.contest;

import com.gift.dto.contest.ContestImgDto;
import com.gift.entity.contest.ContestImg;
import com.gift.entity.request.RequestImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ContestImgRepository extends JpaRepository<ContestImg, Long> {

    List<ContestImg> findByContestIdOrderByIdAsc(Long ContestId);

    ContestImgDto findByContestId(Long contestId);

    @Transactional
    void deleteByContestId(Long contestId);

}
