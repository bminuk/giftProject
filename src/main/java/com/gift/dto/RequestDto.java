package com.gift.dto;

import com.gift.entity.Member;
import com.gift.entity.Request;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RequestDto {

    private Long id;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String requestTitle;

    private String requestRange;

    private String requestDate;

    private String requestContent;
    
    //저장 후 수정할 때 이미지 정보 저장하는 리스트
    private List<RequestImgDto> requestImgDtoList = new ArrayList<>();

    //이미지 아이디 저장하는 리스트
    //수정 시 이미지 아이디 담아둘 용도
    private List<Long> requestImgIds = new ArrayList<>();
    private static ModelMapper modelMapper = new ModelMapper();

    public Request createRequest() {
        return modelMapper.map(this, Request.class);
    }

    public static RequestDto of(Request request) {
        return modelMapper.map(request, RequestDto.class);
    }

}
