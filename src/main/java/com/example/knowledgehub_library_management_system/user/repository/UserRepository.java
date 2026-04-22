package com.example.knowledgehub_library_management_system.user.repository;

import com.example.knowledgehub_library_management_system.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long>
{

    Optional<User> findByEmail(String email);

}
