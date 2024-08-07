package com.casava.library.controller;

import com.casava.library.domain.Loan;
import com.casava.library.domain.UserLoans;
import com.casava.library.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> borrowBook(@Valid @RequestBody Loan loan){
        return ResponseEntity.ok(loanService.borrowBook(loan));
    }

    @PutMapping("/{id}/{returnDate}")
    public ResponseEntity<Loan> returnBook(@PathVariable String id,
                                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnDate){
        return ResponseEntity.ok(loanService.returnBook(id,returnDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> singleLoan(@PathVariable String id){
        return ResponseEntity.ok(loanService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Loan>> allLoans(){
        return ResponseEntity.ok(loanService.findAll());
    }

    @GetMapping("/user")
    public ResponseEntity<UserLoans> getUserLoans(@RequestParam String userId){
        return ResponseEntity.ok(loanService.getUserLoans(userId));
    }
}
