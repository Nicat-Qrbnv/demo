package com.example.demo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CourseResponseDTO {
    private String title;
    private List<StudentResponseDTO> students;
}