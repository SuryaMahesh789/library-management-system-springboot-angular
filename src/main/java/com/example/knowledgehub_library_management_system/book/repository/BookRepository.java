package com.example.knowledgehub_library_management_system.book.repository;

import com.example.knowledgehub_library_management_system.common.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>
{


}
