package com.example.demo.controller;

import com.example.demo.config.feignClients.Demo1Client;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Demo1Controller {
    private final Demo1Client demo1;

    @GetMapping("/demo1greet")
    public String greeting() {
        return demo1.callDemo1();
    }
}