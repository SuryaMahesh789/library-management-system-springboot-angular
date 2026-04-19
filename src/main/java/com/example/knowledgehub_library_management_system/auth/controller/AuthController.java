package com.example.knowledgehub_library_management_system.auth.controller;

import com.example.knowledgehub_library_management_system.auth.dto.RegisterRequestDTO;
import com.example.knowledgehub_library_management_system.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequestDTO request)
    {
        return userService.registerUser(request);
    }


}
