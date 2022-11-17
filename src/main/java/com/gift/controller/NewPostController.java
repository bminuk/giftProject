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

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/request/{requestId}")
    public String requestDt1(@PathVariable("requestId") Long requestId, Model model) {
        try {
            RequestDto requestDto = requestService.getRequestDt1(requestId);
            model.addAttribute("requestDto", requestDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 글입니다.");
            model.addAttribute("requestDto", new RequestDto());
            return "/newPost/request";
        }
        return "/newPost/request";
    }

    @PostMapping(value = "/request/{requestId}")
    public String requestUpdate(@Valid RequestDto requestDto, BindingResult bindingResult, @RequestParam("requestImgFile") List<MultipartFile> requestImgFileList, Model model) {
        if(bindingResult.hasErrors()) {
            return "/newPost/request";
        }
//        if(requestImgFileList.get(0).isEmpty() && requestDto.getId() == null) {
//            model.addAttribute("errorMessage", "이미지 필수 입력 값입니다.");
//        }
         try {
             requestService.updateRequest(requestDto, requestImgFileList);
         }catch (Exception e) {
             model.addAttribute("errorMessage", "수정 중 에러가 발생했습니다.");
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

    @GetMapping(value = "/sell/{sellId}")
    public String sellDt1(@PathVariable("sellId") Long sellId, Model model) {
        try {
            SellDto sellDto = sellService.getSellDt1(sellId);
            model.addAttribute("sellDto", sellDto);
        } catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage","존재하지 않는 글입니다.");
            model.addAttribute("sellDto", new SellDto());
            return "/newPost/sell";
        }
        return "/newPost/sell";
    }
    @PostMapping(value = "/sell/{sellId}")
    public String sellUpdate(@Valid SellDto sellDto, BindingResult bindingResult, @RequestParam("sellImgFile") List<MultipartFile> sellImgFileList, Model model) {
        if(bindingResult.hasErrors()) {
            return "/newPost/sell";
        }
        try {
            sellService.updateSell(sellDto, sellImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "수정 중 에러가 발생했습니다.");
            return "/newPost/sell";
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

    //PathVariable는 링크 url 정보
    @GetMapping(value = "/contest/{contestId}")
    public String contestDt1(@PathVariable("contestId") Long contestId, Model model) {
        //조회 모델 담아 뷰로 전달
        try {
            ContestDto contestDto = contestService.getContestDt1(contestId);
            model.addAttribute("contestDto", contestDto);
        }catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 게시글입니다.");
            model.addAttribute("contestDto", new ContestDto());
            return "/newPost/contest";
        }
        return "/newPost/contest";
    }

    @PostMapping(value = "/contest/{contestId}")
    public String contestUpdate(@Valid ContestDto contestDto, BindingResult bindingResult, @RequestParam("contestImgFile") List<MultipartFile> contestImgFileList, Model model) {
        if(bindingResult.hasErrors()) {
            return "/newPost/contest";
        }
        try {
            contestService.updateContest(contestDto, contestImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "수정 중 에러가 발생했습니다.");
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

    @GetMapping(value = "/exchange/{exchangeId}")
    public String exchangeDt1(@PathVariable("exchangeId") Long exchangeId, Model model) {
        //조회 모델 담아 뷰로 전달
        try {
            ExchangeDto exchangeDto = exchangeService.getExchangeDt1(exchangeId);
            model.addAttribute("exchangeDto", exchangeDto);
        }catch (EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 게시글입니다.");
            model.addAttribute("exchangeDto", new ExchangeDto());
            return "/newPost/exchange";
        }
        return "/newPost/exchange";
    }

    @PostMapping(value = "/exchange/{exchangeId}")
    public String exchangeUpdate(@Valid ExchangeDto exchangeDto, BindingResult bindingResult, @RequestParam("exchangeImgFile") List<MultipartFile> exchangeImgFileList, Model model) {
        if(bindingResult.hasErrors()) {
            return "/newPost/exchange";
        }
        try {
            exchangeService.updateExchange(exchangeDto, exchangeImgFileList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "수정 중 에러가 발생했습니다.");
            return "/newPost/exchange";
        }
        return "redirect:/";
    }

}
