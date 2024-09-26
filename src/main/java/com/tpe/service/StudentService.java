package com.tpe.service;

import com.tpe.dto.StudentDTO;
import com.tpe.entity.Student;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public void createStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email is already exist!!");
        }

        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public void deleteStudent(Long id) {

        Student student = getStudentById(id); // Bu method yukarıda var!!
        studentRepository.delete(student);

    }

    public void updateStudent(Long id, StudentDTO studentDTO) {

        boolean existEmail = studentRepository.existsByEmail(studentDTO.getEmail());

        Student student = getStudentById(id);


        if (existEmail && ! student.getEmail().equals(studentDTO.getEmail())) {

            throw new ConflictException("Email already exist!!");

        /*
            1. mrc, mrc --> TRUE && FALSE (UPDATE)
            2. mrc, brk (brk DB de var) --> TRUE && TRUE (UPDATE YOK)
            3. mrc, brc (brc DB de yok) --> FALSE && TRUE (UPDATE)

        */
        }

        student.setName(studentDTO.getFirstName());
        student.setLastname(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());

        studentRepository.save(student);

    }

    public Page<Student> getAllStudentWithPage(Pageable pageable) {

        return studentRepository.findAll(pageable);
    }

    public List<Student> findAllEqualsGrade(Integer grade) {

        return studentRepository.findAllEqualsGradeWithSQL(grade);
    }

    public StudentDTO getStudentDTOById(Long id) {

        return studentRepository.findStudentDTOByIdWithJPQL(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));


    }
}
