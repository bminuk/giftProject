package com.gift.repository.exchange;

import com.gift.dto.exchange.MainExchangeDto;
import com.gift.dto.exchange.QMainExchangeDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.dto.request.QMainRequestDto;
import com.gift.entity.exchange.QExchange;
import com.gift.entity.exchange.QExchangeImg;
import com.gift.entity.request.QRequest;
import com.gift.entity.request.QRequestImg;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class ExchangeRepositoryCustomImpl implements ExchangeRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public ExchangeRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
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
}
