package com.example.knowledgehub_library_management_system.book.controller;


import com.example.knowledgehub_library_management_system.book.dto.BookRequestDTO;
import com.example.knowledgehub_library_management_system.book.service.BookService;
import com.example.knowledgehub_library_management_system.common.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


//    Create Book - ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createBook(@RequestBody BookRequestDTO request)
    {
        return bookService.createBook(request);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public List<Book> getAllBooks()
    {
        return bookService.getAllBooks();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id)
    {
        return bookService.getBookById(id);
    }

//    Update Book - ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public String updateBook(@PathVariable Long id,@RequestBody BookRequestDTO request)
    {
        return bookService.updateBook(id,request);
    }


//    Delete Book - ADMIN

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id)
    {
        return bookService.deleteBook(id);
    }

       // User - Request Book
       @PreAuthorize("hasRole('USER')")
       @PostMapping("/request/{bookId}")
       public String requestBook(@PathVariable Long bookId)
       {
           return bookService.requestBook(bookId);
       }

//       ADMIN - Approve Request

        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/request/{id}/approve")
        public String approve(@PathVariable Long id)
        {
            return bookService.approveRequest(id);
        }


//       ADMIN - Reject Request

        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/request/{id}/reject")
        public String reject(@PathVariable Long id)
        {
            return bookService.rejectRequest(id);
        }
}
