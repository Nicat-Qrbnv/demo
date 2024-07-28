package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query ("select s from Student s where s.fullName = :fullName")
    Student findByName(@Param("fullName") String fullName);

    @Query (nativeQuery = true, value = "SELECT * FROM student s WHERE s.score = :score")
    List<Student> findByScore(double score);
}