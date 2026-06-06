package org.example.dtos.AuthenticationDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupRequestDTO (

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is Required")
        @Email(message = "Please enter a valid email")
        String email,

        @NotBlank(message = "Password is Required")
        @Size(min = 8 , message = "Password must be at least 8 characters")
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$!%&*]).{8,20}$" ,
          message = "Password must be 8–20 characters with at least one uppercase, "
                + "lowercase, digit, and special character (@#$!%&*)"
        )
        String password

) { }
