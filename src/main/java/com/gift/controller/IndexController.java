package com.gift.controller;

import com.gift.dto.MemberDto;
import com.gift.dto.SellDto;
import com.gift.entity.Sell;
import com.gift.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

@Autowired
private SellRepository sellRepository;
    @GetMapping(value = "/")
    public String main(Model model){
        List<Sell> sells = sellRepository.findAll();
        model.addAttribute("sells",sells);
        return "index";
    }



}
