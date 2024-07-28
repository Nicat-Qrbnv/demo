package com.example.demo.controller;

import com.example.demo.service.GoodReadsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/good")
@RequiredArgsConstructor
public class GoodReadsController {
    private final GoodReadsService goodReadsService;

    @GetMapping("/year")
    public String greet(@RequestParam String input) {
        return goodReadsService.getYear(input);
    }
}