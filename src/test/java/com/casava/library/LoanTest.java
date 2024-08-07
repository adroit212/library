package com.casava.library;

import com.casava.library.controller.LoanController;
import com.casava.library.domain.Loan;
import com.casava.library.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class LoanTest {
    @InjectMocks
    private LoanController loanController;
    @MockBean
    private LoanService loanService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void borrowBookTest() throws Exception {
        mockMvc.perform(post("/loans")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(buildLoan())))
                .andExpect(status().isOk());
    }

    @Test
    public void returnBookTest() throws Exception{
        mockMvc.perform(put("/loans/66b363f21821a66a4d2dddb7/2024-08-20")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    private Loan buildLoan(){
        Loan loan = new Loan();
        loan.setBookId("66b363f21821a66a4d2dddb7");
        loan.setUserId("66b364bee8bf001962705501");
        loan.setLoanDate(LocalDate.now());

        return loan;
    }
}
