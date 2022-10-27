package com.gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/myPage")
@Controller
public class PersonalController {

    @GetMapping
    public String myPage(){
        return "/personal/myPage";
    }
}
