package com.example.knowledgehub_library_management_system.book.controller;


import com.example.knowledgehub_library_management_system.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController
{
    private final BookService bookService;

    @GetMapping("/test-db")
    public String testDB()
    {
        return bookService.createDummyBook();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addBook()
    {
        return "Book Added By Admin...";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userAccess()
    {
        return "User Access Granted";
    }

}
