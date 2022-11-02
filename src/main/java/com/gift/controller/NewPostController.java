package com.gift.controller;

import com.gift.dto.ContestDto;
import com.gift.dto.SellDto;
import com.gift.service.ContestService;
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

    @Autowired
    private ContestService contestService;

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
    public String saveSell(SellDto sellDto){
        System.out.println(sellDto);
        sellService.saveSell(sellDto);
        return "redirect:/";
    }


    @GetMapping(value = "/contest")
    public String contest(Model model){
        model.addAttribute("contestDto",new ContestDto());
        return "/newPost/contest";
    }
    @PostMapping(value = "/contest")
    public String saveContest(ContestDto contestDto){
        System.out.println("=========================>>>>>"+contestDto.getTitle());
        System.out.println("=========================>>>>>"+contestDto.getContent());
        System.out.println("=========================>>>>>"+contestDto.getSkill());
        System.out.println("contestDto 비어 있어요???????????");
        contestService.saveContest(contestDto);
        return "redirect:/";
    }
}
