package com.casava.library.service;

import com.casava.library.domain.Book;
import com.casava.library.domain.Loan;
import com.casava.library.domain.UserLoanData;
import com.casava.library.domain.UserLoans;
import com.casava.library.model.LoanEntity;
import com.casava.library.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final ModelMapper modelMapper;
    private final LoanRepository loanRepository;
    private final BookService bookService;
    private final UserService userService;

    private Loan save(Loan loan){
        LoanEntity loanEntity = modelMapper.map(loan,LoanEntity.class);
        return modelMapper.map(loanRepository.save(loanEntity),Loan.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public Loan borrowBook(Loan loan){
        if(loan.getLoanDate() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Loan date is required");
        }
        if(!userService.existsById(loan.getUserId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Selected user not found");
        }
        Book book = bookService.findById(loan.getBookId());
        if(book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Selected book not found");
        }
        if(book.getCopiesAvailable() < 1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No copy available for selected book");
        }
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookService.save(book);
        loan.setReturnDate(null);
        return save(loan);
    }

    @Transactional(rollbackFor = Exception.class)
    public Loan returnBook(String id, LocalDate returnDate){
        Loan loan = findById(id);
        if(loan == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Record not found");
        }
        if(loan.getReturnDate() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Book already returned");
        }
        Book book = bookService.findById(loan.getBookId());
        if(book == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Selected book not found");
        }
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookService.save(book);
        loan.setReturnDate(returnDate);
        return save(loan);
    }

    public Loan findById(String id){
        return loanRepository.findById(id).map(entity ->
                modelMapper.map(entity,Loan.class)).orElse(null);
    }

    public List<Loan> findAll(){
        return loanRepository.findAll().stream().map(entity->
                modelMapper.map(entity,Loan.class)).toList();
    }

    public List<Loan> findAllByUserIdOrderByLoanDateDesc(String userId){
        return loanRepository.findAllByUserIdOrderByLoanDateDesc(userId).stream().map(entity -> modelMapper.map(entity,Loan.class)).toList();
    }

    public UserLoans getUserLoans(String userId){
        List<Loan> loans = findAllByUserIdOrderByLoanDateDesc(userId);
        List<UserLoanData> loanData = loans.stream().map(loan ->
                UserLoanData.builder().loan(loan).book(bookService.findById(loan.getBookId())).build()).toList();
        return UserLoans.builder()
                .userId(userId)
                .userLoanData(loanData)
                .build();
    }
}
