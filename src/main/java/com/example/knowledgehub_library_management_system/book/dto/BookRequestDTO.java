package com.example.knowledgehub_library_management_system.book.dto;


import lombok.Data;

@Data
public class BookRequestDTO
{
    private String title;
    private String author;
    private Long genreId;
    private int totalCopies;
    private String location;
}
