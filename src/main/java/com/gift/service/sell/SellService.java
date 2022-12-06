package com.gift.service.sell;

import com.gift.auth.PrincipalDetails;
import com.gift.dto.search.SearchDto;
import com.gift.dto.sell.MainSellDto;
import com.gift.dto.sell.SellDto;
import com.gift.entity.member.Member;
import com.gift.entity.sell.Sell;
import com.gift.entity.sell.SellImg;
import com.gift.repository.category.CategoryRepository;
import com.gift.repository.sell.SellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final CategoryRepository categoryRepository;

    public Long saveSell(SellDto sellDto, List<MultipartFile> sellImgFileList, Authentication authentication) throws Exception {

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Member member = principalDetails.getUser();

        Sell sell = sellDto.createSell();
        sell.setMember(member);
        sellRepository.save(sell);

        for (int i = 0; i < sellImgFileList.size(); i++) {
            SellImg sellImg = new SellImg();
            sellImg.setSell(sell);
            if (i == 0) {
                sellImg.setSellRepImgYn("Y");
            } else {
                sellImg.setSellRepImgYn("N");
            }

            sellImgService.saveSellImg(sellImg, sellImgFileList.get(i));

        }
        return sell.getId();

    }

    @Transactional(readOnly = true)
    public Page<MainSellDto> getMainSellPage(Pageable pageable) {
        return sellRepository.getMainSellPage(pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainSellDto> getVideoPage(Pageable pageable) {
        return categoryRepository.getVideoPage(pageable);
    }
    @Transactional(readOnly = true)
    public Page<MainSellDto> getItPage(Pageable pageable) {
        return categoryRepository.getItPage(pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainSellDto> getDesignPage(Pageable pageable) {
        return categoryRepository.getDesignPage(pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainSellDto> getLanguagePage(Pageable pageable) {
        return categoryRepository.getLanguagePage(pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainSellDto> getHobbyPage(Pageable pageable) {
        return categoryRepository.getHobbyPage(pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainSellDto> getSearchSellPage(SearchDto searchDto, Pageable pageable) {
        return sellRepository.getSearchSellPage(searchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainSellDto> getMemberSellPage(Long id, Pageable pageable) {
        return sellRepository.getMemberSellPage(id, pageable);
    }
}
