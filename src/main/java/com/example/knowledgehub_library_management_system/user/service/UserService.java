package com.example.knowledgehub_library_management_system.user.service;

import com.example.knowledgehub_library_management_system.auth.dto.RegisterRequestDTO;
import com.example.knowledgehub_library_management_system.common.entity.Role;
import com.example.knowledgehub_library_management_system.common.entity.User;
import com.example.knowledgehub_library_management_system.role.repository.RoleRepository;
import com.example.knowledgehub_library_management_system.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String,Object> getCurrentUserDetails()
    {
        Object prinicipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) prinicipal;

        Map<String ,Object> response = new HashMap<>();
        response.put("email",user.getEmail());
        response.put("role",user.getRole().getName());


        return response;
    }

    public Map<String,Object> getDashboard()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        Map<String,Object>response = new HashMap<>();

        if(user.getRole().getName().equals("ADMIN"))
        {
            response.put("message","Admin Dashboard");
            response.put("totalUsers",userRepository.count());
            response.put("totalBooks",120); // dummy
            response.put("System Status","All Systems Running");
        }
        else
        {
            response.put("message","User Dashboard");
            response.put("availableBooks",50); // dummy
            response.put("recommended", List.of("Atomic Habits","Clean Code"));
        }

        return response;
    }

    public Map<String,Object> getUserById(Long userId)
    {
        // 1. Get logged-in user
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 2. Fetch requested user from DB
        User requestedUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Check access rules
        // Admin → full access
        if(loggedInUser.getRole().getName().equals("ADMIN"))
        {
            return buildUserResponse(requestedUser);
        }

        // User accessing own data
        if(loggedInUser.getId().equals(userId))
        {
            return buildUserResponse(requestedUser);
        }

        throw new RuntimeException("Access Denied: you can view only your profile");

    }

    private Map<String,Object> buildUserResponse(User user)
    {
        Map<String,Object> response = new HashMap<>();

        response.put("id",user.getId());
        response.put("role",user.getRole().getName());
        response.put("name",user.getName());
        response.put("email",user.getEmail());
        response.put("password",user.getPassword());
        return response;
    }

}
