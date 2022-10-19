package com.gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "newPost")
public class NewPostController {

    @GetMapping(value = "/newSell")
    public String newSell(){
        return "newPost/newSell";
    }
}
