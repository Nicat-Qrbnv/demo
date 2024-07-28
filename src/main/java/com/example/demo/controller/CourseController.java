package com.example.demo.controller;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/new")
    public Course addCourse(@RequestBody CourseRequestDTO dto) {
        return courseService.addCourse(dto);
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return courseService.getCourse(id).orElse(new Course());
    }
}