package com.example.demo.service;

import com.example.demo.config.feignClients.GoodReadsClient;
import com.example.demo.dto.goodReadsDto.GoodReadsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodReadsService {
    private final GoodReadsClient goodReadsClient;
    @Value("${host}")
    private String host;
    @Value("${key}")
    private String key;

    public String getYear (String input) {
        List<GoodReadsResponse> books = goodReadsClient.searchBooks(host, key, input, 1);
        return books.getFirst().getPublishedYear();
    }
}