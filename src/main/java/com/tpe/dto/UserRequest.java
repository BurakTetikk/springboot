package com.tpe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Give a correct text!!")
    private String firstName;

    @NotBlank(message = "Give a correct text!!")
    private String lastName;

    @NotBlank(message = "Please provide username!!")
    @Size(min = 5, max = 10, message = "Please provide a username min {min}, max {max} chars long!!")
    private String userName;

    @NotBlank(message = "Please provide password!!")
    private String password;

}
