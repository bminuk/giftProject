package com.gift.controller;

import com.gift.auth.PrincipalDetails;
import com.gift.entity.contest.Contest;
import com.gift.entity.exchange.Exchange;
import com.gift.entity.member.Member;
import com.gift.entity.request.Request;
import com.gift.entity.sell.Sell;
import com.gift.entity.sell.SellImg;
import com.gift.repository.contest.ContestImgRepository;
import com.gift.repository.contest.ContestRepository;
import com.gift.repository.exchange.ExchangeImgRepository;
import com.gift.repository.exchange.ExchangeRepository;
import com.gift.repository.request.RequestImgRepository;
import com.gift.repository.request.RequestRepository;
import com.gift.repository.sell.SellImgRepository;
import com.gift.repository.sell.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/details")
public class DetailsController {



    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private SellImgRepository sellImgRepository;
    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private ContestImgRepository contestImgRepository;
    @Autowired
    private ExchangeRepository exchangeRepository;
    @Autowired
    private ExchangeImgRepository exchangeImgRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestImgRepository requestImgRepository;

    @GetMapping(value = "/sellDetail/{sellId}")
    public String sellDetail(@PathVariable("sellId")Long sellId, Model model){
        Sell sell = sellRepository.findById(sellId).get();
        Member member = sell.getMember();
        model.addAttribute("member",member);
        model.addAttribute("sell",sell);

//        String url = sell.getSellImg().get(0).toString();
//        model.addAttribute("url",url);
//        System.out.println(url);

        return "/details/sellDetail";
    }

    @GetMapping(value = "/sellDetailsTest/{sellId}")
    public String sellDetailsTest(@PathVariable("sellId")Long sellId, Model model, Authentication authentication){
        Sell sell = sellRepository.findById(sellId).get();
        Member member = sell.getMember();
        String uuid = UUID.randomUUID().toString();

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member authMember = principalDetails.getUser();
        model.addAttribute("authMember",authMember);

        model.addAttribute("member",member);

        model.addAttribute("sell",sell);
        model.addAttribute("uuid",uuid);
        return "/details/sellDetailsTest";
    }

    @GetMapping(value = "/contestDetail/{contestId}")
    public String contestDetail(@PathVariable("contestId")Long contestId, Model model, Authentication authentication){
        Contest contest = contestRepository.findById(contestId).get();

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member authMember = principalDetails.getUser();
        model.addAttribute("authMember",authMember);

        Member member = contest.getMember();
        model.addAttribute("member",member);
        model.addAttribute("contest",contest);

        return "/details/contestDetail";
    }

    @GetMapping(value = "/exchangeDetail/{exchangeId}")
    public String exchangeDetail(@PathVariable("exchangeId")Long exchangeId, Model model, Authentication authentication){
        Exchange exchange = exchangeRepository.findById(exchangeId).get();
        Member member = exchange.getMember();

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member authMember = principalDetails.getUser();
        model.addAttribute("authMember",authMember);

        model.addAttribute("member",member);
        model.addAttribute("exchange",exchange);

        return "/details/exchangeDetail";
    }
    @GetMapping(value = "/requestDetail/{requestId}")
    public String requestDetail(@PathVariable("requestId")Long requestId, Model model, Authentication authentication){
        Request request = requestRepository.findById(requestId).get();
        Member member = request.getMember();

        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        Member authMember = principalDetails.getUser();
        model.addAttribute("authMember",authMember);

        model.addAttribute("member",member);
        model.addAttribute("request",request);

        return "/details/requestDetail";
    }

    @GetMapping(value = "/sellDelete/{sellId}")
    public String sellDelete(@PathVariable("sellId")Long sellId){
        sellImgRepository.deleteBySellId(sellId);
        sellRepository.deleteById(sellId);

//        String url = sell.getSellImg().get(0).toString();
//        model.addAttribute("url",url);
//        System.out.println(url);

        return "redirect:/";
    }

    @GetMapping(value = "/contestDelete/{contestId}")
    public String contestDelete(@PathVariable("contestId")Long contestId){
        contestImgRepository.deleteByContestId(contestId);
        contestRepository.deleteById(contestId);
        return "redirect:/";
    }

    @GetMapping(value = "/exchangeDelete/{exchangeId}")
    public String exchangeDelete(@PathVariable("exchangeId")Long exchangeId){
        exchangeImgRepository.deleteByExchangeId(exchangeId);
        exchangeRepository.deleteById(exchangeId);
        return "redirect:/";
    }

    @GetMapping(value = "/requestDelete/{requestId}")
    public String requestDelete(@PathVariable("requestId")Long requestId){
        requestImgRepository.deleteByRequestId(requestId);
        requestRepository.deleteById(requestId);
        return "redirect:/";
    }


}
