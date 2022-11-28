package com.gift.repository.sell;

import com.gift.constant.Category;
import com.gift.dto.request.MainRequestDto;
import com.gift.dto.request.QMainRequestDto;
import com.gift.dto.search.SearchDto;
import com.gift.dto.sell.MainSellDto;
import com.gift.dto.sell.QMainSellDto;
import com.gift.entity.request.QRequest;
import com.gift.entity.request.QRequestImg;
import com.gift.entity.sell.QSell;
import com.gift.entity.sell.QSellImg;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class SellRepositoryCustomImpl implements SellRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public  SellRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if(StringUtils.equals("title", searchBy)) {
            return QSell.sell.title.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("content", searchBy)) {
            return QSell.sell.content.like("%" + searchQuery + "%");
        }
        return null;
    }
    @Override
    public Page<MainSellDto> getMainSellPage(Pageable pageable) {
        QSell sell = QSell.sell;
        QSellImg sellImg = QSellImg.sellImg;

        QueryResults<MainSellDto> results = queryFactory
                .select(
                        new QMainSellDto(
                                sell.id,
                                sell.title,
                                sellImg.sellImgUrl)
                )
                .from(sellImg)
                .join(sellImg.sell, sell)
                .where(sellImg.sellRepImgYn.eq("Y"))
                .orderBy(sell.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainSellDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainSellDto> getSearchSellPage(SearchDto searchDto, Pageable pageable) {
        QSell sell = QSell.sell;
        QSellImg sellImg = QSellImg.sellImg;

        QueryResults<MainSellDto> results = queryFactory
                .select(
                        new QMainSellDto(
                                sell.id,
                                sell.title,
                                sellImg.sellImgUrl
                        )
                )
                .from(sellImg)
                .join(sellImg.sell, sell)
                .where(searchByLike(searchDto.getSearchBy(), searchDto.getSearchQuery()))
                .orderBy(QSell.sell.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainSellDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable,total)
                ;    }

    @Override
    public Page<MainSellDto> getMemberSellPage(Long id, Pageable pageable) {
        QSell sell = QSell.sell;
        QSellImg sellImg = QSellImg.sellImg;

        QueryResults<MainSellDto> results = queryFactory
                .select(
                        new QMainSellDto(
                                sell.id,
                                sell.title,
                                sellImg.sellImgUrl)
                )
                .from(sellImg)
                .join(sellImg.sell, sell)
                .where(sell.member.id.eq(id))
                .orderBy(sell.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainSellDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }


}
