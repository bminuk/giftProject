package com.gift.controller;
import com.gift.dto.search.SearchDto;
import com.gift.dto.sell.MainSellDto;
import com.gift.dto.sell.SellImgDto;
import com.gift.entity.sell.Sell;
import com.gift.entity.sell.SellImg;
import com.gift.repository.sell.SellImgRepository;
import com.gift.repository.sell.SellRepository;
import com.gift.service.sell.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private SellImgRepository sellImgRepository;
    @Autowired
    private SellService sellService;
    @GetMapping(value = "/")
    public String sellBoard(SearchDto searchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        model.addAttribute("searchDto", new SearchDto());

        Page<MainSellDto> sells = sellService.getMainSellPage(pageable);

        model.addAttribute("sells", sells);
        model.addAttribute("maxPage", 5);

        return "/category/all";
    }



}
