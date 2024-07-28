package com.example.demo.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StudentRequestDTO {
    private String fullName;
    private double score;
    private List<Long> courses;
}