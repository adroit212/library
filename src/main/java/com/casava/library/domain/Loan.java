package com.casava.library.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Loan {
    private String id;
    private String bookId;
    private String userId;
    private LocalDate loanDate;
    private LocalDate returnDate;
}
