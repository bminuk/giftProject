package com.gift.controller;
import com.gift.dto.sell.SellImgDto;
import com.gift.entity.sell.Sell;
import com.gift.entity.sell.SellImg;
import com.gift.repository.sell.SellImgRepository;
import com.gift.repository.sell.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

@Autowired
private SellRepository sellRepository;
@Autowired
private SellImgRepository sellImgRepository;
    @GetMapping(value = "/")
    public String main(@PageableDefault(size = 6) Pageable pageable, Model model){

        Page<Sell> sells = sellRepository.findAll(pageable);
        try {
            Sell sell = sells.getContent().get(0);
            List<SellImg> sellImgs = sellImgRepository.findBySellIdOrderByIdAsc(sell.getId());
            model.addAttribute("imgsrc",sellImgs.get(0).getSellImgUrl());
            System.out.println(sellImgs.get(0).getSellImgUrl());
        }catch (Exception e){
            System.out.println("sell 아직 없음");
        }

        //List<SellImg> sellImgs = sellImgRepository.findBySellIdOrderByIdAsc(sell.getId());
        //System.out.println(sellImgs);
        //List<SellImg> sellImgs = sellImgRepository.findBySellIdOrderByIdAsc(cxz);

        int startPage = Math.max(1, sells.getPageable().getPageNumber() - 4);
        int endPage = Math.min(sells.getPageable().getPageNumber()+4, sells.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sells",sells);


        return "index";
    }



}
