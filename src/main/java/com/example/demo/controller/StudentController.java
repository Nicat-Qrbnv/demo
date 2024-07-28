package com.example.demo.controller;

import com.example.demo.dto.request.StudentRequestDTO;
import com.example.demo.dto.response.StudentResponseDTO;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<StudentResponseDTO> create(@RequestBody StudentRequestDTO dto) {
        try {
            return ResponseEntity.ok(studentService.createStudent(dto));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Set<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/byScore")
    public ResponseEntity<Set<StudentResponseDTO>> getStudentsByScore(@RequestParam long score) {
        return ResponseEntity.ok(studentService.getStudentsByScore(score));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.deleteStudent(id));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}