package com.example.knowledgehub_library_management_system.genre.repository;

import com.example.knowledgehub_library_management_system.common.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long>
{
}
