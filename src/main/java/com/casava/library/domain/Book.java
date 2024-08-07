package com.casava.library.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    private String id;
    private String title;
    private String author;
    private LocalDate publishedYear;
    private String isbn;
    private String copiesAvailable;
}
