package com.example.knowledgehub_library_management_system.user.service;

import com.example.knowledgehub_library_management_system.auth.dto.RegisterRequestDTO;
import com.example.knowledgehub_library_management_system.common.entity.Role;
import com.example.knowledgehub_library_management_system.common.entity.User;
import com.example.knowledgehub_library_management_system.role.repository.RoleRepository;
import com.example.knowledgehub_library_management_system.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService
{
    
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(RegisterRequestDTO request)
    {

        Role role = roleRepository.findAll()
                                    .stream()
                                    .filter(r -> r.getName().equals("USER"))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

//        normal way of setting password from user request while registration
//        user.setPassword(request.getPassword());

//        Encoding the password before storing into DB/ Before Registering the user
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        user.setVerified(false);
        user.setRole(role);

        userRepository.save(user);
        return "User registered successfully...";
    }

    public String promoteAdmin(Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        Role adminRole = roleRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("ADMIN role not found"));

        user.setRole(adminRole);
        userRepository.save(user);

        return "User Promoted to ADMIN SuccessFully";

    }

}
