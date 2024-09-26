package com.tpe.repository;

import com.tpe.dto.StudentDTO;
import com.tpe.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    // JPQL *******
    @Query("select s from Student s where s.grade=:pGrade")
    List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);


    //Native SQL ******
    @Query(value = "select * from Student s where s.grade=:pGrade", nativeQuery = true)
    List<Student> findAllEqualsGradeWithSQL(@Param("pGrade") Integer grade);


    @Query("select new com.tpe.dto.StudentDTO(s) from Student s where s.id=:id")
    Optional<StudentDTO> findStudentDTOByIdWithJPQL(@Param("id") Long id);
}
