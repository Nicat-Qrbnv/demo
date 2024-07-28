package com.example.demo.config.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient (name = "MyDemo1", url = "localhost:8081")
public interface Demo1Client {
    @GetMapping("/greet")
    String callDemo1 ();
}