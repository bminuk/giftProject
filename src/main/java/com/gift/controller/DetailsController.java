package com.gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/details")
public class DetailsController {

    @GetMapping(value = "/sellDetail")
    public String sellDetail(){
        return "/details/sellDetail";
    }

}
