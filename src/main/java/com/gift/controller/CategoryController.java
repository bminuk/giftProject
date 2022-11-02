package com.gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @GetMapping(value = "/all")
    public String all(){
        return "category/all";
    }

    @GetMapping(value = "/video")
    public String video(){
        return "category/video";
    }
    @GetMapping(value = "/design")
    public String design(){
        return "category/design";
    }
    @GetMapping(value = "/it")
    public String it(){
        return "category/it";
    }
    @GetMapping(value = "/lang")
    public String lang(){
        return "category/lang";
    }
    @GetMapping(value = "/hobby")
    public String hobby(){
        return "category/hobby";
    }

}
