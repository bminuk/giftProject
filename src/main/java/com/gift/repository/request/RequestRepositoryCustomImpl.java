package com.gift.repository.request;

import com.gift.dto.request.MainRequestDto;
import com.gift.dto.request.QMainRequestDto;
import com.gift.entity.request.QRequest;
import com.gift.entity.request.QRequestImg;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class RequestRepositoryCustomImpl implements RequestRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public RequestRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


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
}
