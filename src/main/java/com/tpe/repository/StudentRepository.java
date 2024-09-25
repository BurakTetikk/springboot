package com.tpe.repository;

import com.tpe.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    // JPQL *******
    @Query("select s from Student s where s.grade=:pGrade")
    List<Student> findAllEqualsGrade(@Param("pGrade") Integer grade);
}
