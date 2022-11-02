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


//    private List<RequestImageDto> requestImageDtoList = new ArrayList<>();
//
//    private List<Long> requestImageIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Request createRequest() {
        return modelMapper.map(this, Request.class);
    }

    public static RequestDto of(Request request) {
        return modelMapper.map(request, RequestDto.class);
    }

}
