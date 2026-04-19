package com.example.knowledgehub_library_management_system.book.controller;


import com.example.knowledgehub_library_management_system.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController
{
    private final BookService bookService;

    @GetMapping("/test-db")
    public String testDB()
    {
        return bookService.createDummyBook();
    }

}
