package com.gift.controller;

import com.gift.entity.member.Member;
import com.gift.entity.sell.Sell;
import com.gift.entity.sell.SellImg;
import com.gift.repository.sell.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/details")
public class DetailsController {



    @Autowired
    private SellRepository sellRepository;

    @GetMapping(value = "/sellDetail/{sellId}")
    public String sellDetail(@PathVariable("sellId")Long sellId, Model model){
        Sell sell = sellRepository.findById(sellId).get();
        Member member = sell.getMember();
        model.addAttribute("member",member);
        model.addAttribute("sell",sell);

//        String url = sell.getSellImg().get(0).toString();
//        model.addAttribute("url",url);
//        System.out.println(url);

        return "/details/sellDetail";
    }

    @GetMapping(value = "/sellDetailsTest/{sellId}")
    public String sellDetailsTest(@PathVariable("sellId")Long sellId, Model model){
        Sell sell = sellRepository.findById(sellId).get();
        Member member = sell.getMember();
        model.addAttribute("member",member);
        model.addAttribute("sell",sell);

        return "/details/sellDetailsTest";
    }



}
