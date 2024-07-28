//package com.example.demo.service;
//
//import com.example.demo.dto.response.StudentRespDto;
//import com.example.demo.model.Student;
//import com.example.demo.repository.StudentRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ScheduledService {
//    private final StudentRepository studentRepository;
//    private final ModelMapper mapper;
//    private Student recommendedStudent;
//    private final StudentService studentService;
//
//
//    @Scheduled(fixedDelay = 5000)
//    public void randomRecommendation() {
//        List<Student> all = studentRepository.findAll();
//        if (!all.isEmpty()) {
//            Random random = new Random();
//            int randomIndex = random.nextInt(0, all.size());
//            recommendedStudent = all.get(randomIndex);
//        } else {
//            recommendedStudent = new Student();
//        }
//    }
//
//    @Scheduled(cron = "3/5 49 * * * *")
//    public void task () {
//        log.info("Task started");
//    }
//    public void deleteTask () {
//        Student deleted = new Student();
//        for (Student student : studentRepository.findAll()) {
//            if (Instant.now().minusMillis(TimeUnit.DAYS.toMillis(5)).toEpochMilli() > studentDate) {
//                studentService.deleteStudent(student.getId());
//                deleted = student;
//            }
//        }
//        log.info("Deleted student: {}", deleted);
//    }
//
//    public StudentRespDto getRecommendedStudent() {
//        return mapper.map(recommendedStudent, StudentRespDto.class);
//    }
//}