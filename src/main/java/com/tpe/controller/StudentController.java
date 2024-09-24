package com.tpe.controller;

import com.tpe.entity.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {

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
}
