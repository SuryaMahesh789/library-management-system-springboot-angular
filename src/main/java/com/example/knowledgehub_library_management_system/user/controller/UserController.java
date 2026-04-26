package com.example.knowledgehub_library_management_system.user.controller;

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
    public String promoteUser(@PathVariable Long userId)
    {
        return userService.promoteAdmin(userId);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public Map<String,Object> getCurrentUser()
    {
        return userService.getCurrentUserDetails();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/dashboard")
    public Map<String,Object> getDashboard()
    {
        return userService.getDashboard();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public Map<String,Object> getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }

}
