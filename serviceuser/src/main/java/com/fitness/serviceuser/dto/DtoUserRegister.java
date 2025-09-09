package com.fitness.serviceuser.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DtoUserRegister {

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid email Format")
    private String email;
   @NotBlank(message = "Password field is Required")
    private String password;
    private String firstName;
    private String lastName;


}
