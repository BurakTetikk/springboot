package com.tpe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor // final işaretli fieldlar için constructor oluşturur
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Firstname can not be null!!")
    @NotBlank(message = "Firstname can not be whitespace") //space karakterinin eklenmemesi için kullanılır
    @Size(  min = 3,
            max = 25,
            message = "Firstname '${validatedValue}' must be between {min} and {max}")
    @Column(nullable = false, length = 25) //Hibernate kontrolü
    //private final String name;
    private String name;

    @Column(nullable = false, length = 25)
    //private final String lastname;
    private String lastname;

    //private final Integer grade;
    private Integer grade;

    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "Provide valid email!!")  // xxx@yy.com
    //private final String email;
    private String email;

    //private final String phoneNumber;
    private String phoneNumber;

    @Setter(AccessLevel.NONE) // setter metodu yok
    private LocalDateTime createDate = LocalDateTime.now();


    // @JsonIgnore  bu anotasyon açılırsa Jsonla books verisi gelmez
    @OneToMany(mappedBy = "student")
    private List<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", grade=" + grade +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
