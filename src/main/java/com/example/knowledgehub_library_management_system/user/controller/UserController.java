package com.example.knowledgehub_library_management_system.user.controller;

import com.example.knowledgehub_library_management_system.common.dto.ApiResponse;
import com.example.knowledgehub_library_management_system.common.entity.User;
import com.example.knowledgehub_library_management_system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController{

    private final UserService userService;

    // Admin Promoting User to Admin
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/promote/{userId}")
    public ApiResponse<String> promoteUser(@PathVariable Long userId)
    {
        userService.promoteAdmin(userId);
        return new ApiResponse<>(200, "User promoted to ADMIN successfully", null);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ApiResponse<Map<String,Object>> getCurrentUser()
    {
         return new ApiResponse<>(200,"User Details fetched",userService.getCurrentUserDetails());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/dashboard")
    public ApiResponse<Map<String,Object>> getDashboard()
    {
        return new ApiResponse<>(200,"Dashboard Fetched",userService.getDashboard());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ApiResponse<Map<String,Object>> getUserById(@PathVariable Long id)
    {
        return new ApiResponse<>(200,"User Fetched",userService.getUserById(id));
    }

}
