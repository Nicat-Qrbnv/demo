package com.example.demo.serviceTests;

import com.example.demo.dto.request.StudentRequestDTO;
import com.example.demo.dto.response.StudentResponseDTO;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import com.example.demo.util.CollectionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {

    @InjectMocks
    private StudentService service;
    @Mock
    private StudentRepository repo;
    @Mock
    private CourseRepository courseRepo;
    @Mock
    private ModelMapper mapper;
    @Mock
    private CollectionMapper colMapper;

    private StudentRequestDTO request;
    private StudentResponseDTO response1;
    private StudentResponseDTO response2;
    private Student student1;
    private Student student2;
    private Course course1;
    private Course course2;

    @BeforeEach
    void init() {
        request = StudentRequestDTO.builder().fullName("student1").score(99.0).courses(List.of(1L)).build();

        student1 = Student.builder().id(1L).fullName("student1").score(99.0).build();
        student2 = Student.builder().id(2L).fullName("student2").score(99.0).build();

        course1 = Course.builder().id(1L).title("course1").students(Set.of(student1, student2)).build();
        course2 = Course.builder().id(2L).title("course2").students(Set.of(student1, student2)).build();

        response1 = StudentResponseDTO.builder().fullName("student1").score(99.0).build();
        response2 = StudentResponseDTO.builder().fullName("student2").score(99.0).build();
    }

    @Test
    public void createStudent_Successful() {
        var spy = Mockito.spy(service);

        when(mapper.map(request, Student.class)).thenReturn(student1);
        when(spy.findCourses(any())).thenReturn(Set.of(course1, course2));
        when(repo.save(student1)).thenReturn(student1);
        when(mapper.map(student1, StudentResponseDTO.class)).thenReturn(response1);

        var methodResponse = spy.createStudent(request);

        Assertions.assertNotNull(methodResponse);
        Assertions.assertEquals(methodResponse, response1);
    }

    @Test
    public void createStudent_ThrowsException() {
        var spy = Mockito.spy(service);

        when(mapper.map(request, Student.class)).thenReturn(student1);
        when(spy.findCourses(any())).thenReturn(Set.of(course1, course2));
        when(repo.save(student1)).thenThrow(DataIntegrityViolationException.class);

        Assertions.assertThrows(
                DataIntegrityViolationException.class,
                () -> spy.createStudent(request)
        );
    }

    @Test
    public void findCourse_IfThereAreIds_ReturnsAll() {
        List<Long> ids = new ArrayList<>(List.of(1L, 2L));
        when(courseRepo.findById(ids.get(0))).thenReturn(Optional.ofNullable(course1));
        when(courseRepo.findById(ids.get(1))).thenReturn(Optional.ofNullable(course2));

        Set<Course> courses = service.findCourses(ids);

        Assertions.assertNotNull(courses);
        Assertions.assertTrue(courses.contains(course1));
        Assertions.assertTrue(courses.contains(course2));
    }

    @Test
    public void findCourse_IfThereAreIds_ReturnsFirst() {
        List<Long> ids = new ArrayList<>(List.of(1L, 2L));
        when(courseRepo.findById(ids.get(0))).thenReturn(Optional.ofNullable(course1));
        when(courseRepo.findById(ids.get(1))).thenReturn(Optional.empty());

        Set<Course> courses = service.findCourses(ids);

        Assertions.assertNotNull(courses);
        Assertions.assertTrue(courses.contains(course1));
        Assertions.assertFalse(courses.contains(course2));
    }

    @Test
    public void findCourse_IfThereAreIds_ReturnsNone() {
        List<Long> ids = new ArrayList<>(List.of(1L, 2L));
        when(courseRepo.findById(ids.get(0))).thenReturn(Optional.empty());
        when(courseRepo.findById(ids.get(1))).thenReturn(Optional.empty());

        Set<Course> courses = service.findCourses(ids);

        Assertions.assertNotNull(courses);
        Assertions.assertFalse(courses.contains(course1));
        Assertions.assertFalse(courses.contains(course2));
    }

    @Test
    public void getAllStudents_ReturnsAllStudents() {
        var students = List.of(student1, student2);
        when(repo.findAll()).thenReturn(students);
        when(colMapper.map(students, StudentResponseDTO.class))
                .thenReturn(Set.of(response1, response2));

        var methodResponse = service.getAllStudents();
        Assertions.assertNotNull(methodResponse);
        Assertions.assertTrue(methodResponse.contains(response1));
        Assertions.assertTrue(methodResponse.contains(response2));
    }

    @Test
    public void getAllStudents_ReturnsNone() {
        var students = new ArrayList<Student>();
        when(repo.findAll()).thenReturn(students);
        when(colMapper.map(students, StudentResponseDTO.class)).thenReturn(new HashSet<>());

        var methodResponse = service.getAllStudents();
        Assertions.assertNotNull(methodResponse);
        Assertions.assertTrue(methodResponse.isEmpty());
    }

    @Test
    public void getStudentsByScore_ReturnsAll() {
        var students = List.of(student1, student2);
        when(repo.findByScore(99.0)).thenReturn(students);
        when(colMapper.map(students, StudentResponseDTO.class))
                .thenReturn(Set.of(response1, response2));

        var methodResponse = service.getStudentsByScore(99.0);

        Assertions.assertNotNull(methodResponse);
        Assertions.assertTrue(methodResponse.contains(response1));
        Assertions.assertTrue(methodResponse.contains(response2));
    }

    @Test
    public void deleteStudent_Successful() {
        doNothing().when(repo).deleteById(1L);
        Assertions.assertDoesNotThrow(() -> service.deleteStudent(1L));
    }

    @Test
    public void deleteStudent_ThrowsException() {
        doThrow(DataIntegrityViolationException.class).when(repo).deleteById(1L);
        Assertions.assertThrows(
                DataIntegrityViolationException.class,
                () -> service.deleteStudent(1L)
        );
    }
}