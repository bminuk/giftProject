package com.gift.repository.contest;

import com.gift.entity.contest.ContestImg;
import com.gift.entity.request.RequestImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestImgRepository extends JpaRepository<ContestImg, Long> {

    List<ContestImg> findByContestIdOrderByIdAsc(Long ContestId);
}
