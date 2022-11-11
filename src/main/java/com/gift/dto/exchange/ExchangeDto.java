package com.gift.dto.exchange;

import com.gift.constant.Category;
import com.gift.dto.request.RequestDto;
import com.gift.dto.request.RequestImgDto;
import com.gift.entity.exchange.Exchange;
import com.gift.entity.request.Request;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ExchangeDto {

    private Long id;

    private String exchangeTitle;

    private String exchangeIntro;

    private String exchangeDate;

    private Category exchangeCategory;

    //저장 후 수정할 때 이미지 정보 저장하는 리스트
    private List<RequestImgDto> exchangeImgDtoList = new ArrayList<>();

    //이미지 아이디 저장하는 리스트
    //수정 시 이미지 아이디 담아둘 용도
    private List<Long> exchangeImgIds = new ArrayList<>();
    private static ModelMapper modelMapper = new ModelMapper();

    //model mapper를 사용하면 빌더로 하나하나 객체 매핑하지 않아도 됨
    public Exchange createExchange() {
        return modelMapper.map(this, Exchange.class);
    }

    public static ExchangeDto of(Exchange exchange) {
        return modelMapper.map(exchange, ExchangeDto.class);
    }


}
