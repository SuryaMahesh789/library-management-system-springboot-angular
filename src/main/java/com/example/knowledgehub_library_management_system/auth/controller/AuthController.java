package com.example.knowledgehub_library_management_system.auth.controller;

import com.example.knowledgehub_library_management_system.auth.dto.LoginRequestDTO;
import com.example.knowledgehub_library_management_system.auth.dto.RegisterRequestDTO;
import com.example.knowledgehub_library_management_system.auth.service.AuthService;
import com.example.knowledgehub_library_management_system.common.dto.ApiResponse;
import com.example.knowledgehub_library_management_system.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody @Valid RegisterRequestDTO request)
    {
        return new ApiResponse<>(200,"User Registered Successfully",userService.registerUser(request));
    }

    @GetMapping("/login")
    public ApiResponse<String> login(@RequestBody @Valid LoginRequestDTO request)
    {
        return new ApiResponse<>(200,"Login Successful",authService.login(request));
    }

}
