package com.gift.controller;

import com.gift.dto.SellDto;
import com.gift.entity.Sell;
import com.gift.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/newPost")
@Controller
public class NewPostController {

    @Autowired
    private SellService sellService;

    @GetMapping(value = "/newSell")
    public String newSell(){
        return "/newPost/newSell";
    }

    @GetMapping(value = "/sell")
    public String sell(Model model){
        model.addAttribute("sellDto",new SellDto());

        return "/newPost/sell";
    }

    @PostMapping(value = "/sell")
    public String saveSell(SellDto sellDto, Model model){
        System.out.println(sellDto);
        sellService.saveSell(sellDto);
        return "redirect:/";
    }



    @GetMapping(value = "/contest")
    public String contest(){
        return "/newPost/contest";
    }
}
