package com.gift.repository.exchange;

import com.gift.dto.contest.MainContestDto;
import com.gift.dto.contest.QMainContestDto;
import com.gift.dto.exchange.MainExchangeDto;
import com.gift.dto.exchange.QMainExchangeDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.dto.request.QMainRequestDto;
import com.gift.dto.search.SearchDto;
import com.gift.entity.contest.QContest;
import com.gift.entity.contest.QContestImg;
import com.gift.entity.exchange.QExchange;
import com.gift.entity.exchange.QExchangeImg;
import com.gift.entity.request.QRequest;
import com.gift.entity.request.QRequestImg;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class ExchangeRepositoryCustomImpl implements ExchangeRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public ExchangeRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if(StringUtils.equals("title", searchBy)) {
            return QExchange.exchange.exchangeTitle.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("content", searchBy)) {
            return QExchange.exchange.exchangeIntro.like("%" + searchQuery + "%");
        }
        return null;
    }


    @Override
    public Page<MainExchangeDto> getMainExchangePage(Pageable pageable) {
        QExchange exchange = QExchange.exchange;
        QExchangeImg exchangeImg = QExchangeImg.exchangeImg;

        QueryResults<MainExchangeDto> results = queryFactory
                .select(
                        new QMainExchangeDto(
                                exchange.id,
                                exchange.exchangeTitle,
                                exchangeImg.exchangeImgUrl)
                )
                .from(exchangeImg)
                .join(exchangeImg.exchange, exchange)
                .where(exchangeImg.exchangeRepImgYn.eq("Y"))
                .orderBy(exchange.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainExchangeDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainExchangeDto> getSearchExchangePage(SearchDto searchDto, Pageable pageable) {
        QExchange exchange = QExchange.exchange;
        QExchangeImg exchangeImg = QExchangeImg.exchangeImg;

        QueryResults<MainExchangeDto> results = queryFactory
                .select(
                        new QMainExchangeDto(
                                exchange.id,
                                exchange.exchangeTitle,
                                exchangeImg.exchangeImgUrl
                        )
                )
                .from(exchangeImg)
                .join(exchangeImg.exchange, exchange)
                .where(searchByLike(searchDto.getSearchBy(), searchDto.getSearchQuery()))
                .orderBy(QExchange.exchange.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainExchangeDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable,total)
                ;    }
}
