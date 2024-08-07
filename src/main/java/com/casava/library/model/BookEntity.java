package com.casava.library.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document(collection = "book")
public class BookEntity {
    @Id
    private String id;
    private String title;
    private String author;
    private Integer publishedYear;
    private String isbn;
    private Integer copiesAvailable;
}
