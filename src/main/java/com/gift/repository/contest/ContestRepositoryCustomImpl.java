package com.gift.repository.contest;

import com.gift.dto.contest.MainContestDto;
import com.gift.dto.contest.QMainContestDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.dto.request.QMainRequestDto;
import com.gift.entity.contest.QContest;
import com.gift.entity.contest.QContestImg;
import com.gift.entity.request.QRequest;
import com.gift.entity.request.QRequestImg;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class ContestRepositoryCustomImpl implements ContestRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public ContestRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MainContestDto> getMainContestPage(Pageable pageable) {
        QContest contest = QContest.contest;
        QContestImg contestImg = QContestImg.contestImg;

        QueryResults<MainContestDto> results = queryFactory
                .select(
                        new QMainContestDto(
                                contest.id,
                                contest.title,
                                contestImg.contestImgUrl)
                )
                .from(contestImg)
                .join(contestImg.contest, contest)
                .where(contestImg.contestRepImgYn.eq("Y"))
                .orderBy(contest.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainContestDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
