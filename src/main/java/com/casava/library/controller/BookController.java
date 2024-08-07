package com.casava.library.controller;

import com.casava.library.domain.Book;
import com.casava.library.enumeration.ResponseType;
import com.casava.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book){
        return ResponseEntity.ok(bookService.create(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @Valid @RequestBody Book book){
        return ResponseEntity.ok(bookService.update(id,book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseType> deleteBook(@PathVariable String id){
        return ResponseEntity.ok(bookService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> singleBook(@PathVariable String id){
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Book>> allBooks(){
        return ResponseEntity.ok(bookService.findAll());
    }
}
