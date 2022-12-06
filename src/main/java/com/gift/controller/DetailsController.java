package com.gift.controller;

import com.gift.entity.contest.Contest;
import com.gift.entity.exchange.Exchange;
import com.gift.entity.member.Member;
import com.gift.entity.request.Request;
import com.gift.entity.sell.Sell;
import com.gift.entity.sell.SellImg;
import com.gift.repository.contest.ContestRepository;
import com.gift.repository.exchange.ExchangeRepository;
import com.gift.repository.request.RequestRepository;
import com.gift.repository.sell.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ContestRepository contestRepository;
    @Autowired
    private ExchangeRepository exchangeRepository;
    @Autowired
    private RequestRepository requestRepository;

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
    public String sellDetailsTest(@PathVariable("sellId")Long sellId, Model model){
        Sell sell = sellRepository.findById(sellId).get();
        Member member = sell.getMember();
        String uuid = UUID.randomUUID().toString();

        model.addAttribute("member",member);
        model.addAttribute("sell",sell);
        model.addAttribute("uuid",uuid);
        return "/details/sellDetailsTest";
    }

    @GetMapping(value = "/contestDetail/{contestId}")
    public String contestDetail(@PathVariable("contestId")Long contestId, Model model){
        Contest contest = contestRepository.findById(contestId).get();

        Member member = contest.getMember();
        model.addAttribute("member",member);
        model.addAttribute("contest",contest);

        return "/details/contestDetail";
    }

    @GetMapping(value = "/exchangeDetail/{exchangeId}")
    public String exchangeDetail(@PathVariable("exchangeId")Long exchangeId, Model model){
        Exchange exchange = exchangeRepository.findById(exchangeId).get();
        Member member = exchange.getMember();
        model.addAttribute("member",member);
        model.addAttribute("exchange",exchange);

        return "/details/exchangeDetail";
    }
    @GetMapping(value = "/requestDetail/{requestId}")
    public String requestDetail(@PathVariable("requestId")Long requestId, Model model){
        Request request = requestRepository.findById(requestId).get();
        Member member = request.getMember();
        model.addAttribute("member",member);
        model.addAttribute("request",request);

        return "/details/requestDetail";
    }



}
