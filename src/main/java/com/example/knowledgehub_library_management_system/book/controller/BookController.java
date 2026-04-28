package com.example.knowledgehub_library_management_system.book.controller;


import com.example.knowledgehub_library_management_system.book.dto.BookRequestDTO;
import com.example.knowledgehub_library_management_system.book.service.BookService;
import com.example.knowledgehub_library_management_system.common.dto.ApiResponse;
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
    public ApiResponse<String> testDB()
    {
        return new ApiResponse<>(200,"Test Successful",bookService.createDummyBook());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ApiResponse<String> addBook()
    {
        return new ApiResponse<>(200,"Book Added By Admin...",null);
    }



    // 🔹 USER TEST API (Optional)
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ApiResponse<String> userAccess() {
        return new ApiResponse<>(200, "User access granted", null);
    }

//    Create Book - ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<Void> createBook(@RequestBody BookRequestDTO request)
    {
        return new ApiResponse<>(200,bookService.createBook(request),null);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ApiResponse<List<Book>> getAllBooks()
    {
        return new ApiResponse<>(200,"Books Fetched Successfully",bookService.getAllBooks());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ApiResponse<Book> getBook(@PathVariable Long id)
    {
        return new ApiResponse<>(200,"Book Fetched Successfully",bookService.getBookById(id));
    }

//    Update Book - ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<Void> updateBook(@PathVariable Long id,@RequestBody BookRequestDTO request)
    {
        bookService.updateBook(id, request);
        return new ApiResponse<>(200, "Book updated successfully", null);
    }


//    Delete Book - ADMIN

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBook(@PathVariable Long id)
    {
        bookService.deleteBook(id);
        return new ApiResponse<>(200, "Book deleted successfully", null);    }

       // User - Request Book
       @PreAuthorize("hasRole('USER')")
       @PostMapping("/request/{bookId}")
       public ApiResponse<Void> requestBook(@PathVariable Long bookId) {
           bookService.requestBook(bookId);
           return new ApiResponse<>(200, "Book request created successfully", null);
       }

//       ADMIN - Approve Request

        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/request/{id}/approve")
        public ApiResponse<Void> approve(@PathVariable Long id) {
            bookService.approveRequest(id);
            return new ApiResponse<>(200, "Request approved", null);
        }


//       ADMIN - Reject Request

        @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/request/{id}/reject")
        public ApiResponse<Void> reject(@PathVariable Long id)
        {
            bookService.rejectRequest(id);
            return new ApiResponse<>(200, "Request rejected", null);
        }
}
