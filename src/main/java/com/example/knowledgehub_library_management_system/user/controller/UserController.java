package com.example.knowledgehub_library_management_system.user.controller;

import com.example.knowledgehub_library_management_system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController{

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/promote/{userId}")
    public String promoteUser(@PathVariable Long userId)
    {
        return userService.promoteAdmin(userId);
    }


}
