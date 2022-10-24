package com.gift.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/newPost")
@Controller
public class NewPostController {

    @GetMapping(value = "/newSell")
    public String newSell(){
        return "/newPost/newSell";
    }

    @GetMapping(value = "/sell")
    public String sell(){
        return "/newPost/sell";
    }

    @GetMapping(value = "/contest")
    public String contest(){
        return "/newPost/contest";
    }
}
