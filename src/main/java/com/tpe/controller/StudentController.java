package com.tpe.controller;

import com.tpe.dto.StudentDTO;
import com.tpe.entity.Student;
import com.tpe.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() { // ResponseEntity = HTTP Status Code + Entity

        List<Student> studentList = studentService.getAll();

        return ResponseEntity.ok(studentList);

    }


    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> createStudent(@Valid @RequestBody Student student) {

        studentService.createStudent(student);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is created successfully!!");
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }


    @GetMapping("/id")// Endpoint içinde 'kalem = 1' şeklinde id değeri alır
    public ResponseEntity<Student> getStudentById(@RequestParam("id") Long id) {
        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentByIdWithPath(@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable("id") Long id) {

        studentService.deleteStudent(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is deleted successfully!!");
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable("id") Long id,
                                                             @Valid @RequestParam StudentDTO studentDTO) {

        studentService.updateStudent(id, studentDTO);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Student is updated successfully!!");
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getAllStudentWithPage(@RequestParam("page") int page,
                                                               @RequestParam("size") int size,
                                                               @RequestParam("sort") String prop,
                                                               @RequestParam("direction")Sort.Direction direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<Student> studentPage = studentService.getAllStudentWithPage(pageable);

        return ResponseEntity.ok(studentPage);
    }

    // Get ALL Student By Grade (JPQL)
    @GetMapping("/grade/{grade}")
    public ResponseEntity<List<Student>> getStudentEqualsGrade(@PathVariable("grade") Integer grade) {

        List<Student> studentList = studentService.findAllEqualsGrade(grade);

        return ResponseEntity.ok(studentList);
    }

    // DB den direkt DTO olarak data alabilir miyim?
    @GetMapping("/query")
    public ResponseEntity<StudentDTO> getStudentDTOWithJPQL(@RequestParam("id") Long id) {
        StudentDTO studentDTO = studentService.getStudentDTOById(id);

        logger.warn("****** StudentDTO ******{}", studentDTO.getEmail());

        return ResponseEntity.ok(studentDTO);
    }


    @GetMapping("/welcome")
    public String welcome(HttpServletRequest request) {
        logger.warn("--------------------Welcome------------------- : {}", request.getServletPath());

        return "Student Controllar a Hoş Geldiniz!!!";
    }







}
