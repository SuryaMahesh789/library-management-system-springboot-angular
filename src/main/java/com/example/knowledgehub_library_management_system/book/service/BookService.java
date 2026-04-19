package com.example.knowledgehub_library_management_system.book.service;

import com.example.knowledgehub_library_management_system.book.repository.BookRepository;
import com.example.knowledgehub_library_management_system.common.entity.Book;
import com.example.knowledgehub_library_management_system.common.entity.Genre;
import com.example.knowledgehub_library_management_system.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


}
