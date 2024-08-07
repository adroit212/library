package com.casava.library.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "loan")
public class LoanEntity {
    @Id
    private String id;
    private String bookId;
    private String userId;
    private LocalDate loanDate;
    private LocalDate returnDate;
}
