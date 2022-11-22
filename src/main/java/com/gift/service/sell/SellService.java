package com.gift.service.sell;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.sell.SellDto;
import com.gift.entity.member.Member;
import com.gift.entity.sell.Sell;
import com.gift.entity.sell.SellImg;
import com.gift.repository.sell.SellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SellService {

    private final SellImgService sellImgService;
    private final SellRepository sellRepository;

    public Long saveSell(SellDto sellDto, List<MultipartFile> sellImgFileList, Authentication authentication) throws Exception{

        Sell sell = new Sell();
        sell.setContent(sellDto.getContent());
        sell.setTitle(sellDto.getTitle());
        sell.setMoney(sellDto.getMoney());

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member member = principalDetails.getUser();

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

}
