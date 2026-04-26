package com.example.knowledgehub_library_management_system.book.service;

import com.example.knowledgehub_library_management_system.book.dto.BookRequestDTO;
import com.example.knowledgehub_library_management_system.book.repository.BookRepository;
import com.example.knowledgehub_library_management_system.common.entity.Book;
import com.example.knowledgehub_library_management_system.common.entity.Genre;
import com.example.knowledgehub_library_management_system.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService
{

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public String createDummyBook()
    {
        Genre genre = new Genre();
        genre.setName("Fiction");
        genreRepository.save(genre);

        Book book = new Book();
        book.setTitle("Atomic Habits");
        book.setAuthor("James Clear");
        book.setGenre(genre);
        book.setTotalCopies(10);
        book.setAvailableCopies(10);
        book.setLocation("Floor 1 - Rack A");

        bookRepository.save(book);

        return "Book Saved Successfully";

    }

    public String createBook(BookRequestDTO request)
    {
        Genre genre = genreRepository.findById(request.getGenreId()).orElseThrow(() -> new RuntimeException("Genre not found"));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setGenre(genre);
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies());
        book.setLocation(request.getLocation());

        bookRepository.save(book);

        return request.getTitle() + "Book Created Successfully...";

    }

    public List<Book> getAllBooks()
    {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id)
    {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not Found"));
    }

    public String updateBook(Long id,BookRequestDTO request)
    {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies());
        book.setLocation(request.getLocation());

        bookRepository.save(book);

        return "Book updated successfully";

    }

    public String deleteBook(Long id)
    {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(book);

        return "Book Deleted Successfully...";
    }




}
