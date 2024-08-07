package com.casava.library;

import com.casava.library.controller.BookController;
import com.casava.library.domain.Book;
import com.casava.library.service.BookService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BookTest {
    @InjectMocks
    private BookController bookController;
    @MockBean
    private BookService bookService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createBookTest() throws Exception {
        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(buildBook())))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBookTest() throws Exception{
        mockMvc.perform(put("/books/66b363f21821a66a4d2dddb7")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(buildBook())))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteBookTest() throws Exception{
        mockMvc.perform(delete("/books/66b363f21821a66a4d2dddb7")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    private Book buildBook(){
        Book book = new Book();
        book.setPublishedYear(2024);
        book.setTitle("Testing Books");
        book.setAuthor("Tester Test");
        book.setIsbn("1827363636");
        book.setCopiesAvailable(5);
        return book;
    }
}
