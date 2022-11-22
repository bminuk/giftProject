package com.gift.controller;

import com.gift.dto.contest.ContestDto;
import com.gift.dto.exchange.ExchangeDto;
import com.gift.dto.request.RequestDto;
import com.gift.dto.sell.SellDto;
import com.gift.service.contest.ContestService;
import com.gift.service.exchange.ExchangeService;
import com.gift.service.request.RequestService;
import com.gift.service.sell.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/newPost")
@Controller
public class NewPostController {

    @Autowired
    private SellService sellService;

    @Autowired
    private ContestService contestService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ExchangeService exchangeService;


    @GetMapping(value = "/newSell")
    public String newSell(){
        return "/newPost/newSell";
    }

    @GetMapping(value = "/request")
    public String request(Model model) {
        model.addAttribute("requestDto", new RequestDto());

        return "/newPost/request";
    }

    @PostMapping(value = "/request")
    public String newRequest(@Valid RequestDto requestDto, BindingResult bindingResult, Authentication authentication, Model model,
                             @RequestParam(value = "requestImgFile", required=false) List<MultipartFile> requestImgFileList) {
        if(bindingResult.hasErrors()) {
            return "/newPost/request";
        }

        if(requestImgFileList.get(0).isEmpty() && requestDto.getId() == null) {
            model.addAttribute("errorMessage", "이미지 삽입은 필수적입니다~");
        }

        try {
            requestService.saveRequest(requestDto, requestImgFileList, authentication);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "글 등록 중 에러 발생하였습니다.");
            return "/newPost/request";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/sell")
    public String sell(Model model){
        model.addAttribute("sellDto",new SellDto());

        return "/newPost/sell";
    }

    @PostMapping(value = "/sell")
    public String saveSell(@Valid SellDto sellDto, BindingResult bindingResult, Authentication authentication, Model model,
                           @RequestParam(value = "sellImgFile", required=false) List<MultipartFile> sellImgFileList){
        if(bindingResult.hasErrors()) {
            return "/newPost/sell";
        }
        if(sellImgFileList.get(0).isEmpty() && sellDto.getId() == null) {
            model.addAttribute("errorMessage", "이미지 삽입은 필수적입니다~");
        }

        try {
            sellService.saveSell(sellDto, sellImgFileList, authentication);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "글 등록 중 에러 발생하였습니다.");
            return "/newPost/request";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/contest")
    public String contest(Model model){
        model.addAttribute("contestDto",new ContestDto());
        return "/newPost/contest";
    }
    @PostMapping(value = "/contest")
    public String saveContest(@Valid ContestDto contestDto, BindingResult bindingResult, Authentication authentication, Model model,
                              @RequestParam(value = "contestImgFile", required=false) List<MultipartFile> contestImgFileList){
//        contestService.saveContest(contestDto);
        if(bindingResult.hasErrors()) {
            return "/newPost/contest";
        }
        if(contestImgFileList.get(0).isEmpty() && contestDto.getId() == null) {
            model.addAttribute("errorMessage", "이미지 삽입은 필수적입니다~");
        }

        try {
            contestService.saveContest(contestDto, contestImgFileList, authentication);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "글 등록 중 에러 발생하였습니다.");
            return "/newPost/contest";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/exchange")
    public String exchange(Model model){
        model.addAttribute("exchangeDto",new ExchangeDto());
        return "/newPost/exchange";
    }
    @PostMapping(value = "/exchange")
    public String saveExchange(@Valid ExchangeDto exchangeDto, BindingResult bindingResult, Authentication authentication, Model model,
                              @RequestParam(value = "exchangeImgFile", required=false) List<MultipartFile> exchangeImgFileList){
        if(bindingResult.hasErrors()) {
            return "/newPost/exchange";
        }
        if(exchangeImgFileList.get(0).isEmpty() && exchangeDto.getId() == null) {
            model.addAttribute("errorMessage", "이미지 삽입은 필수적입니다~");
        }

        try {
            exchangeService.saveExchange(exchangeDto, exchangeImgFileList, authentication);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "글 등록 중 에러 발생하였습니다.");
            return "/newPost/exchange";
        }
        return "redirect:/";
    }
}
