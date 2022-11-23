package com.gift.controller;

import com.gift.dto.contest.MainContestDto;
import com.gift.dto.exchange.MainExchangeDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.entity.contest.Contest;
import com.gift.repository.request.RequestRepository;
import com.gift.service.contest.ContestService;
import com.gift.service.exchange.ExchangeService;
import com.gift.service.request.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {

    private final RequestService requestService;

    private final ExchangeService exchangeService;

    private final ContestService contestService;

    private final RequestRepository requestRepository;

    @GetMapping(value = {"/requestBoard", "/requestBoard/{page}"})
    public String requestBoard(@PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        Page<MainRequestDto> requests = requestService.getMainRequestPage(pageable);

        model.addAttribute("requests", requests);
        model.addAttribute("maxPage", 5);

        return "/category/requestBoard";
    }

    @GetMapping(value = {"/exchangeBoard", "/exchangeBoard/{page}"})
    public String exchangeBoard(@PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        Page<MainExchangeDto> exchanges = exchangeService.getMainExchangePage(pageable);

        model.addAttribute("exchanges", exchanges);
        model.addAttribute("maxPage", 5);
        System.out.println("pppp");
        return "/category/exchangeBoard";
    }

    @GetMapping(value = {"/contestBoard", "/contestBoard/{page}"})
    public String contestBoard(@PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        Page<MainContestDto> contests = contestService.getMainContestPage(pageable);

        model.addAttribute("contests", contests);
        model.addAttribute("maxPage", 5);

        return "/category/contestBoard";
    }

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
