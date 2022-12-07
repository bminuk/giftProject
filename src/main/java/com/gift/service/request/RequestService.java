package com.gift.service.request;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.exchange.ExchangeImgDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.dto.request.RequestDto;
import com.gift.dto.request.RequestImgDto;
import com.gift.dto.search.SearchDto;
import com.gift.dto.sell.MainSellDto;
import com.gift.entity.exchange.Exchange;
import com.gift.entity.member.Member;
import com.gift.entity.request.Request;
import com.gift.entity.request.RequestImg;
import com.gift.repository.request.RequestImgRepository;
import com.gift.repository.request.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final RequestImgService requestImgService;
    private final RequestImgRepository requestImgRepository;


    public Long saveRequest(RequestDto requestDto, List<MultipartFile> requestImgFileList, Authentication authentication) throws Exception {

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member member = principalDetails.getUser();

        Request request = requestDto.createRequest();
        request.setMember(member);
        requestRepository.save(request);

        for(int i=0; i<requestImgFileList.size();i++) {
            RequestImg requestImg = new RequestImg();
            requestImg.setRequest(request);
            if(i==0) {
                requestImg.setRequestRepImgYn("Y");
            } else {
                requestImg.setRequestRepImgYn("N");
            }
            requestImgService.saveRequestImg(requestImg, requestImgFileList.get(i));
        }
        return request.getId();
    }
    @Transactional(readOnly = true)
    public Page<MainRequestDto> getMainRequestPage(Pageable pageable) {
        return requestRepository.getMainRequestPage(pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainRequestDto> getSearchRequestPage(SearchDto searchDto, Pageable pageable) {
        return requestRepository.getSearchRequestPage(searchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainRequestDto> getMemberRequestPage(Long id, Pageable pageable) {
        return requestRepository.getMemberRequestPage(id, pageable);
    }

    public Long updateRequest(RequestDto requestDto, List<MultipartFile> requestImgFileList, Long requestId) throws Exception{

        Request request= requestRepository.findById(requestId).orElseThrow(EntityNotFoundException::new);
        request.updateRequest(requestDto);

        RequestImgDto requestImgDto = requestImgRepository.findByRequestId(requestId);
        requestImgService.updateExchangeImg(requestImgDto.getId(),requestImgFileList.get(0));





        //contest 수정




        return request.getId();
    }
}
