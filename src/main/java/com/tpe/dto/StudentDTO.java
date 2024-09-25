package com.tpe.dto;

import com.tpe.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @NotNull(message = "Firstname can not be null!!")
    @NotBlank(message = "Firstname can not be whitespace")
    @Size(  min = 3,
            max = 25,
            message = "Firstname '${validatedValue}' must be between {min} and {max}")
    private String firstName;

    private String lastName;

    private Integer grade;

    @Email(message = "Provide valid email")
    private String email;

    private String phoneNumber;

    public StudentDTO(Student student) {
        this.firstName = student.getName();
        this.lastName = student.getLastname();
        this.grade = student.getGrade();
        this.email = student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
    }
}
