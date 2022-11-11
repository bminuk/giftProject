package com.gift.dto.sell;

import com.gift.constant.Category;
import com.gift.dto.request.RequestDto;
import com.gift.dto.request.RequestImgDto;
import com.gift.entity.member.Member;
import com.gift.entity.request.Request;
import com.gift.entity.sell.Sell;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SellDto {

    private Long id;

    private Member member;
//     추후 포트 폴리오 엔티티 생성 후 만들어야 할 듯?
//    private Portfolio portfolio;
    private String title;

    private String content;

    private int money;

    private Category sellCategory;

    //저장 후 수정할 때 이미지 정보 저장하는 리스트
    private List<SellImgDto> sellImgDtoList = new ArrayList<>();

    //이미지 아이디 저장하는 리스트
    //수정 시 이미지 아이디 담아둘 용도
    private List<Long> sellImgIds = new ArrayList<>();
    private static ModelMapper modelMapper = new ModelMapper();

    //model mapper를 사용하면 빌더로 하나하나 객체 매핑하지 않아도 됨
    public Sell createSell() {
        return modelMapper.map(this, Sell.class);
    }

    public static SellDto of(Sell sell) {
        return modelMapper.map(sell, SellDto.class);
    }


}
