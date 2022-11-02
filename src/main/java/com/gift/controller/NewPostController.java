package com.gift.controller;

import com.gift.dto.ContestDto;
import com.gift.dto.RequestDto;
import com.gift.dto.SellDto;
import com.gift.repository.RequestRepository;
import com.gift.service.ContestService;
import com.gift.service.RequestService;
import com.gift.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
  
import javax.validation.Valid;

@RequestMapping("/newPost")
@Controller
public class NewPostController {

    @Autowired
    private SellService sellService;

    @Autowired
    private ContestService contestService;

    @Autowired
    private RequestService requestService;

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
    public String saveSell(SellDto sellDto, Authentication authentication){
        System.out.println(sellDto);
        sellService.saveSell(sellDto, authentication);
        return "redirect:/";
    }


    @GetMapping(value = "/contest")
    public String contest(Model model){
        model.addAttribute("contestDto",new ContestDto());
        return "/newPost/contest";
    }
    @PostMapping(value = "/contest")
    public String saveContest(ContestDto contestDto){
        contestService.saveContest(contestDto);
        return "redirect:/";
    }

    @GetMapping(value = "/request")
    public String request(Model model) {
        model.addAttribute("requestDto", new RequestDto());

        return "/newPost/request";
    }

    @PostMapping(value = "/request")
    public String newRequest(@Valid RequestDto requestDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "/newPost/request";
        }

        try {
            requestService.saveRequest(requestDto);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "글 등록 중 에러 발생하였습니다.");
            return "/newPost/request";
        }
        return "redirect:/";
    }
}
