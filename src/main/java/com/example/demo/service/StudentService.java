package com.example.demo.service;

import com.example.demo.dto.request.StudentRequestDTO;
import com.example.demo.dto.response.StudentResponseDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.CollectionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {
    private final CollectionMapper colMapper;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final ModelMapper mapper;

    public StudentResponseDTO createStudent(StudentRequestDTO request) throws DataIntegrityViolationException {
        try {
            Student student = mapper.map(request, Student.class);
            student.setCourses(findCourses(request.getCourses()));
            student = studentRepo.save(student);
            return mapper.map(student, StudentResponseDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Student already exists");
        }
    }

    public Set<Course> findCourses(List<Long> courseIds) {
        Set<Course> courses = new HashSet<>();
        if (courseIds != null) {
            for (Long courseId : courseIds) {
                courseRepo.findById(courseId).ifPresent(courses::add);
            }
        }
        return courses;
    }

    public Set<StudentResponseDTO> getAllStudents() {
        List<Student> found = studentRepo.findAll();
        return colMapper.map(found, StudentResponseDTO.class);
    }

    public Set<StudentResponseDTO> getStudentsByScore(double score) {
        List<Student> found = studentRepo.findByScore(score);
        return colMapper.map(found, StudentResponseDTO.class);
    }

    public String deleteStudent(Long id) {
        try {
            studentRepo.deleteById(id);
            return "Student deleted";
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Student not found");
        }
    }
}