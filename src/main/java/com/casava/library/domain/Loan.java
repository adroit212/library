package com.casava.library.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class Loan {
    private String id;
    @NotBlank(message = "Book ID is required")
    private String bookId;
    @NotBlank(message = "User ID is required")
    private String userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
}
