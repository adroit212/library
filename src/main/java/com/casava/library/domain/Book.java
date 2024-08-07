package com.casava.library.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class Book {
    private String id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @NotNull(message = "Publish year is required")
    private Integer publishedYear;
    @NotBlank(message = "ISBN is required")
    private String isbn;
    @NotNull(message = "Copies Available is required")
    private Integer copiesAvailable;
}
