package com.example.knowledgehub_library_management_system.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDTO
{

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String password;



}
