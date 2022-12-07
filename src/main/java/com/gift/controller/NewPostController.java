package com.gift.controller;

import com.gift.dto.contest.ContestDto;
import com.gift.dto.exchange.ExchangeDto;
import com.gift.dto.request.RequestDto;
import com.gift.dto.sell.SellDto;
import com.gift.entity.contest.Contest;
import com.gift.entity.exchange.Exchange;
import com.gift.entity.request.Request;
import com.gift.entity.sell.Sell;
import com.gift.repository.contest.ContestRepository;
import com.gift.repository.exchange.ExchangeRepository;
import com.gift.repository.request.RequestRepository;
import com.gift.repository.sell.SellRepository;
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


@RequestMapping("/newPost")
@Controller
public class NewPostController {

    @Autowired
    private SellService sellService;
    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private ContestService contestService;

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private RequestService requestService;
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private ExchangeRepository exchangeRepository;


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
    public String requestUpdate(@PathVariable("requestId") Long requestId, Model model){

        try {

            Request request = requestRepository.findById(requestId).orElseThrow(EntityNotFoundException::new);
            RequestDto requestDto = RequestDto.of(request);
            model.addAttribute("requestDto",requestDto);




        } catch(Exception e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("requestDto", new RequestDto());
            return "/newPost/request";
        }

        return "/newPost/request";
    }

    @PostMapping(value = "/request/{requestId}")
    public String exchangeUpdate(@Valid RequestDto requestDto, BindingResult bindingResult, @PathVariable("requestId")Long requestId,
                                 @RequestParam(value = "requestImgFile", required=false) List<MultipartFile> requestImgFileList , Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "상품 이미지는 필수 입력 값 입니다.");
            return "/newPost/request";
        }
        if(requestImgFileList.get(0).isEmpty() && requestDto.getId() == null) {
            model.addAttribute("errorMessage", "이미지 삽입은 필수적입니다~");
            return "/newPost/request";
        }

        try {
            requestService.updateRequest(requestDto, requestImgFileList, requestId);


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
            return "/newPost/sell";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/sell/{sellId}")
    public String sellDtl(@PathVariable("sellId") Long sellId, Model model){

        try {
            Sell sell = sellRepository.findById(sellId).orElseThrow(EntityNotFoundException::new);
            SellDto sellDto = SellDto.of(sell);
            model.addAttribute("sellDto", sellDto);
        } catch(Exception e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("sellDto", new SellDto());
            return "/newPost/sell";
        }

        return "/newPost/sell";
    }
    @PostMapping(value = "/sell/{sellId}")
    public String sellUpdate(@Valid SellDto sellDto, BindingResult bindingResult, @PathVariable("sellId")Long sellId,
                             @RequestParam(value = "sellImgFile", required=false) List<MultipartFile> sellImgFileList , Model model){


        if(bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "/newPost/sell";
        }
        if(sellImgFileList.get(0).isEmpty() && sellDto.getId() == null) {
            model.addAttribute("errorMessage", "이미지 삽입은 필수적입니다~");
            return "/newPost/sell";
        }

        try {

//            Sell sell = sellRepository.findById(sellDto.getId())
//                    .orElseThrow(EntityNotFoundException::new);
//            sell.updateSell(sellDto);

            sellService.updateSell(sellDto, sellImgFileList,sellId);

        } catch (Exception e) {
            model.addAttribute("errorMessage", "글 등록 중 에러 발생하였습니다.");
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

    @GetMapping(value = "/contest/{contestId}")
    public String contestUpdate(@PathVariable("contestId") Long contestId, Model model){

        try {
            Contest contest = contestRepository.findById(contestId).orElseThrow(EntityNotFoundException::new);
            ContestDto contestDto = ContestDto.of(contest);
            model.addAttribute("contestDto", contestDto);
        } catch(Exception e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("contestDto", new ContestDto());
            return "/newPost/contest";
        }

        return "/newPost/contest";
    }

    @PostMapping(value = "/contest/{contestId}")
    public String contestUpdate(@Valid ContestDto contestDto, BindingResult bindingResult, @PathVariable("contestId")Long contestId,
                             @RequestParam(value = "contestImgFile", required=false) List<MultipartFile> contestImgFileList , Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "상품 이미지는 필수 입력 값 입니다.");
            return "/newPost/contest";
        }
        if(contestImgFileList.get(0).isEmpty() && contestDto.getId() == null) {
            model.addAttribute("errorMessage", "이미지 삽입은 필수적입니다~");
            return "/newPost/contest";
        }

        try {
            contestService.updateContest(contestDto, contestImgFileList, contestId);


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
    @GetMapping(value = "/exchange/{exchangeId}")
    public String exchangeUpdate(@PathVariable("exchangeId") Long exchangeId, Model model){

        try {
            Exchange exchange = exchangeRepository.findById(exchangeId).orElseThrow(EntityNotFoundException::new);
            ExchangeDto exchangeDto = ExchangeDto.of(exchange);
            model.addAttribute("exchangeDto", exchangeDto);


        } catch(Exception e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("exchangeDto", new ExchangeDto());
            return "/newPost/exchange";
        }

        return "/newPost/exchange";
    }

    @PostMapping(value = "/exchange/{exchangeId}")
    public String exchangeUpdate(@Valid ExchangeDto exchangeDto, BindingResult bindingResult, @PathVariable("exchangeId")Long exchangeId,
                                @RequestParam(value = "exchangeImgFile", required=false) List<MultipartFile> exchangeImgFileList , Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "상품 이미지는 필수 입력 값 입니다.");
            return "/newPost/exchange";
        }
        if(exchangeImgFileList.get(0).isEmpty() && exchangeDto.getId() == null) {
            model.addAttribute("errorMessage", "이미지 삽입은 필수적입니다~");
            return "/newPost/exchange";
        }

        try {
            exchangeService.updateExchange(exchangeDto, exchangeImgFileList, exchangeId);


        } catch (Exception e) {
            model.addAttribute("errorMessage", "글 등록 중 에러 발생하였습니다.");
            return "/newPost/exchange";
        }
        return "redirect:/";

    }

}
