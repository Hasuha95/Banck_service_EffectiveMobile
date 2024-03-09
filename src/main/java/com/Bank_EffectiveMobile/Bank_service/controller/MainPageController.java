package com.Bank_EffectiveMobile.Bank_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/main")
public class MainPageController {

    @GetMapping(value = "")
    public String mainPage() {
        System.out.println("test");
        return "my_templates/mainHTML/main_page.html";
    }
}
