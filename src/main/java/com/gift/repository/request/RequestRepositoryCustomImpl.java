package com.gift.repository.request;

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

public class RequestRepositoryCustomImpl implements RequestRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public RequestRepositoryCustomImpl(EntityManager em) {

        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if(StringUtils.equals("title", searchBy)) {
            return QRequest.request.requestTitle.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("content", searchBy)) {
            return QRequest.request.requestContent.like("%" + searchQuery + "%");
        }
        return null;
    }


    //타임리프로 값을 넘겨받아 if문으로 어떤 카테고리 검색할지 나누어 작성
    @Override
    public Page<MainRequestDto> getMainRequestPage(Pageable pageable) {
        QRequest request = QRequest.request;
        QRequestImg requestImg = QRequestImg.requestImg;

        QueryResults<MainRequestDto> results = queryFactory
                .select(
                new QMainRequestDto(
                        request.id,
                        request.requestTitle,
                        requestImg.requestImgUrl)
        )
                .from(requestImg)
                .join(requestImg.request, request)
                .where(requestImg.requestRepImgYn.eq("Y"))
                .orderBy(request.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainRequestDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainRequestDto> getSearchRequestPage(SearchDto searchDto, Pageable pageable) {
        QRequest request = QRequest.request;
        QRequestImg requestImg = QRequestImg.requestImg;

        QueryResults<MainRequestDto> results = queryFactory
                .select(
                        new QMainRequestDto(
                                request.id,
                                request.requestTitle,
                                requestImg.requestImgUrl
                        )
                )
                .from(requestImg)
                .join(requestImg.request, request)
                .where(searchByLike(searchDto.getSearchBy(), searchDto.getSearchQuery()))
                .orderBy(QRequest.request.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainRequestDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable,total)
                ;    }

    @Override
    public Page<MainRequestDto> getMemberRequestPage(Long id, Pageable pageable) {
        QRequest request = QRequest.request;
        QRequestImg requestImg = QRequestImg.requestImg;

        QueryResults<MainRequestDto> results = queryFactory
                .select(
                        new QMainRequestDto(
                                request.id,
                                request.requestTitle,
                                requestImg.requestImgUrl)
                )
                .from(requestImg)
                .join(requestImg.request, request)
                .where(request.member.id.eq(id))
                .orderBy(request.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainRequestDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }


}
