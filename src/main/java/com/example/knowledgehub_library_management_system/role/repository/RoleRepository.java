package com.example.knowledgehub_library_management_system.role.repository;

import com.example.knowledgehub_library_management_system.common.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>
{
}
