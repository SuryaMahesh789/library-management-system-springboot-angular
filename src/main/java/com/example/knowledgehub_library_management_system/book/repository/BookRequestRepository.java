package com.example.knowledgehub_library_management_system.book.repository;

import com.example.knowledgehub_library_management_system.common.entity.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRequestRepository extends JpaRepository<BookRequest,Long>
{


}
