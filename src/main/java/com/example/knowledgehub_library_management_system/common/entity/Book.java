package com.example.knowledgehub_library_management_system.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="books")
@Getter
@Setter
public class Book extends BaseEntity
{

    private String title;

    private String author;

    private int totalCopies;

    private int availableCopies;

    private String location;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

}
