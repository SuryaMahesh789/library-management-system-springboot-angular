package com.example.knowledgehub_library_management_system.config;

import com.example.knowledgehub_library_management_system.common.entity.Role;
import com.example.knowledgehub_library_management_system.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer
{
    private final RoleRepository roleRepository;

    @Bean
    CommandLineRunner initRoles()
    {
        return args -> {

            if (roleRepository.count() == 0)
            {

                Role admin = new Role();
                admin.setName("ADMIN");

                Role user = new Role();
                user.setName("USER");

                roleRepository.save(admin);
                roleRepository.save(user);

            }

        };
    }



}
