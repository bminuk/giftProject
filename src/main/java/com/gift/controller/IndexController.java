package com.gift.controller;

import com.gift.entity.Sell;
import com.gift.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

@Autowired
private SellRepository sellRepository;
    @GetMapping(value = "/")
    public String main(@PageableDefault(size = 6) Pageable pageable, Model model){

        Page<Sell> sells = sellRepository.findAll(pageable);


        int startPage = Math.max(1, sells.getPageable().getPageNumber() - 4);
        int endPage = Math.min(sells.getPageable().getPageNumber()+4, sells.getTotalPages());

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("sells",sells);

        return "index";
    }



}
