package com.gift.service.sell;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.sell.SellDto;
import com.gift.dto.sell.SellImgDto;
import com.gift.entity.member.Member;
import com.gift.entity.sell.Sell;
import com.gift.entity.sell.SellImg;
import com.gift.repository.sell.SellImgRepository;
import com.gift.repository.sell.SellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellService {

    private final SellImgService sellImgService;
    private final SellRepository sellRepository;

    private final SellImgRepository sellImgRepository;

    public Long saveSell(SellDto sellDto, List<MultipartFile> sellImgFileList, Authentication authentication) throws Exception{

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member member = principalDetails.getUser();

        Sell sell = sellDto.createSell();
        sell.setMember(member);
        sellRepository.save(sell);

        for(int i=0; i<sellImgFileList.size(); i++) {
            SellImg sellImg = new SellImg();
            sellImg.setSell(sell);
            if(i==0) {
                sellImg.setSellRepImgYn("Y");
            } else {
                sellImg.setSellRepImgYn("N");
            }

            sellImgService.saveSellImg(sellImg, sellImgFileList.get(i));

        }
        return sell.getId();

    }

    @Transactional(readOnly = true)
    public SellDto getSellDt1(Long sellId) {
        List<SellImg> sellImgList = sellImgRepository.findBySellIdOrderByIdAsc(sellId);
        List<SellImgDto> sellImgDtoList = new ArrayList<>();
        for(SellImg sellImg : sellImgList) {
            SellImgDto sellImgDto = SellImgDto.of(sellImg);
            sellImgDtoList.add(sellImgDto);
        }

        Sell sell = sellRepository.findById(sellId)
                .orElseThrow(EntityNotFoundException::new);
        SellDto sellDto = SellDto.of(sell);
        sellDto.setSellImgDtoList(sellImgDtoList);
        return sellDto;
    }

    public Long updateSell(SellDto sellDto, List<MultipartFile> sellImgFileList) throws Exception {

        Sell sell = sellRepository.findById(sellDto.getId()).orElseThrow(EntityNotFoundException::new);
        sell.updateSell(sellDto);

        List<Long> sellImgIds = sellDto.getSellImgIds();

        for(int i=0; i<sellImgFileList.size(); i++) {
            sellImgService.updateSellImg(sellImgIds.get(i), sellImgFileList.get(i));
        }
        return sell.getId();
    }

}
