package com.gift.repository.category;

import com.gift.constant.Category;
import com.gift.dto.sell.MainSellDto;
import com.gift.dto.sell.QMainSellDto;
import com.gift.entity.sell.QSell;
import com.gift.entity.sell.QSellImg;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public  CategoryRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MainSellDto> getVideoPage(Pageable pageable) {
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
                .where((sellImg.sellRepImgYn.eq("Y")), (sell.sellCategory.eq(Category.valueOf("VIDEO"))))
                .orderBy(sell.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MainSellDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainSellDto> getItPage(Pageable pageable) {
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
                .where((sellImg.sellRepImgYn.eq("Y")), (sell.sellCategory.eq(Category.valueOf("IT"))))
                .orderBy(sell.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MainSellDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainSellDto> getDesignPage(Pageable pageable) {
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
                .where((sellImg.sellRepImgYn.eq("Y")), (sell.sellCategory.eq(Category.valueOf("DESIGN"))))
                .orderBy(sell.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MainSellDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainSellDto> getLanguagePage(Pageable pageable) {
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
                .where((sellImg.sellRepImgYn.eq("Y")), (sell.sellCategory.eq(Category.valueOf("LANGUAGE"))))
                .orderBy(sell.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MainSellDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainSellDto> getHobbyPage(Pageable pageable) {
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
                .where((sellImg.sellRepImgYn.eq("Y")), (sell.sellCategory.eq(Category.valueOf("HOBBY"))))
                .orderBy(sell.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<MainSellDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }


}
