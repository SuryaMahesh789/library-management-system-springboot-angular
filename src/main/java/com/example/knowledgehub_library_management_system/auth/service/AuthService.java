package com.example.knowledgehub_library_management_system.auth.service;

import com.example.knowledgehub_library_management_system.auth.dto.LoginRequestDTO;
import com.example.knowledgehub_library_management_system.common.entity.User;
import com.example.knowledgehub_library_management_system.exception.ResourceNotFoundException;
import com.example.knowledgehub_library_management_system.security.JwtUtil;
import com.example.knowledgehub_library_management_system.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String login(LoginRequestDTO request)
    {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        boolean isMatch = passwordEncoder.matches(request.getPassword(),user.getPassword());

        if(!isMatch)
        {
            throw new RuntimeException("Invalid password");
        }

        System.out.println(request.getEmail() + "- Login Successful");

//        without jwt
//        return "Login Successful";

//        with jwt
          return jwtUtil.generateToken(request.getEmail());
    }


}
