package com.Bank_EffectiveMobile.Bank_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/main")
public class MainPageController {


    @GetMapping(value = "")
    public String getPage(){
        System.out.println("test");
        return "fuck/test_page.html";
    }
}
