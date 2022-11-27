package com.gift.controller;

import com.gift.dto.contest.MainContestDto;
import com.gift.dto.exchange.MainExchangeDto;
import com.gift.dto.member.MemberDto;
import com.gift.dto.request.MainRequestDto;
import com.gift.dto.search.SearchDto;
import com.gift.dto.sell.MainSellDto;
import com.gift.service.contest.ContestService;
import com.gift.service.exchange.ExchangeService;
import com.gift.service.request.RequestService;
import com.gift.service.sell.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {

    private final RequestService requestService;

    private final ExchangeService exchangeService;

    private final ContestService contestService;

    private final SellService sellService;

    @GetMapping(value = {"/requestBoard", "/requestBoard/{page}"})
    public String requestBoard(SearchDto searchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        model.addAttribute("searchDto", new SearchDto());

        Page<MainRequestDto> requests = requestService.getMainRequestPage(pageable);

        model.addAttribute("requests", requests);
        model.addAttribute("maxPage", 5);

        return "/category/requestBoard";
    }

    @GetMapping(value = {"/requestBoard/search", "/requestBoard/search/{page}"})
    public String requestSearchBoard(SearchDto searchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        model.addAttribute("searchDto", new SearchDto());

        Page<MainRequestDto> requests = requestService.getSearchRequestPage(searchDto, pageable);
        model.addAttribute("requests", requests);
        model.addAttribute("maxPage", 5);
        return "/category/requestBoard";
    }

    @GetMapping(value = {"/exchangeBoard", "/exchangeBoard/{page}"})
    public String exchangeBoard(SearchDto searchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        model.addAttribute("searchDto", new SearchDto());

        Page<MainExchangeDto> exchanges = exchangeService.getMainExchangePage(pageable);

        model.addAttribute("exchanges", exchanges);
        model.addAttribute("maxPage", 5);
        return "/category/exchangeBoard";
    }

    @GetMapping(value = {"/exchangeBoard/search", "/exchangeBoard/search/{page}"})
    public String exchangeSearchBoard(SearchDto searchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        model.addAttribute("searchDto", new SearchDto());

        Page<MainExchangeDto> exchanges = exchangeService.getSearchExchangePage(searchDto, pageable);
        model.addAttribute("exchanges", exchanges);
        model.addAttribute("maxPage", 5);
        return "/category/exchangeBoard";
    }

    @GetMapping(value = {"/contestBoard", "/contestBoard/{page}"})
    public String contestBoard(SearchDto searchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        model.addAttribute("searchDto", new SearchDto());

        Page<MainContestDto> contests = contestService.getMainContestPage(pageable);

        model.addAttribute("contests", contests);
        model.addAttribute("maxPage", 5);

        return "/category/contestBoard";
    }

    @GetMapping(value = {"/contestBoard/search", "/contestBoard/search/{page}"})
    public String contestSearchBoard(SearchDto searchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        model.addAttribute("searchDto", new SearchDto());

        Page<MainContestDto> contests = contestService.getSearchContestPage(searchDto, pageable);
        model.addAttribute("contests", contests);
        model.addAttribute("maxPage", 5);
        return "/category/contestBoard";
    }

    @GetMapping(value = {"/all", "/all/{page}"})
    public String sellBoard(SearchDto searchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        model.addAttribute("searchDto", new SearchDto());

        Page<MainSellDto> sells = sellService.getMainSellPage(pageable);

        model.addAttribute("sells", sells);
        model.addAttribute("maxPage", 5);

        return "/category/all";
    }

    @GetMapping(value = {"/all/search", "/all/search/{page}"})
    public String sellSearchBoard(SearchDto searchDto, @PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        model.addAttribute("searchDto", new SearchDto());

        Page<MainSellDto> sells = sellService.getSearchSellPage(searchDto, pageable);

        model.addAttribute("sells", sells);
        model.addAttribute("maxPage", 5);
        return "/category/all";
    }

    @GetMapping(value = {"/video", "/video/{page}"})
    public String videoBoard(@PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        Page<MainSellDto> sells = sellService.getVideoPage(pageable);

        model.addAttribute("sells", sells);
        model.addAttribute("maxPage", 5);

        return "/category/video";
    }

    @GetMapping(value = {"/it", "/it/{page}"})
    public String itBoard(@PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        Page<MainSellDto> sells = sellService.getItPage(pageable);

        model.addAttribute("sells", sells);
        model.addAttribute("maxPage", 5);

        return "/category/it";
    }

    @GetMapping(value = {"/design", "/design/{page}"})
    public String designBoard(@PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        Page<MainSellDto> sells = sellService.getDesignPage(pageable);

        model.addAttribute("sells", sells);
        model.addAttribute("maxPage", 5);

        return "/category/design";
    }

    @GetMapping(value = {"/language", "/language/{page}"})
    public String languageBoard(@PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        Page<MainSellDto> sells = sellService.getLanguagePage(pageable);

        model.addAttribute("sells", sells);
        model.addAttribute("maxPage", 5);

        return "/category/language";
    }

    @GetMapping(value = {"/hobby", "/hobby/{page}"})
    public String hobbyBoard(@PathVariable("page") Optional<Integer> page, Model model) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,6);

        Page<MainSellDto> sells = sellService.getHobbyPage(pageable);

        model.addAttribute("sells", sells);
        model.addAttribute("maxPage", 5);

        return "/category/hobby";
    }




}
