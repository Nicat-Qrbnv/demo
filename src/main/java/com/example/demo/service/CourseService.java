package com.example.demo.service;

import com.example.demo.dto.request.CourseRequestDTO;
import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.util.CollectionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourseService {
    private final CollectionMapper colMap;
    private final CourseRepository courseRepo;
    private final ModelMapper mapper;

    public Course addCourse(CourseRequestDTO dto) {
        Course course = mapper.map(dto, Course.class);
        return courseRepo.save(course);
    }

    public Optional<Course> getCourse(Long id) {
        return courseRepo.findById(id);
    }
}