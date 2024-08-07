package com.casava.library.service;

import com.casava.library.domain.Book;
import com.casava.library.enumeration.ResponseType;
import com.casava.library.model.BookEntity;
import com.casava.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    private Book save(Book book){
        BookEntity bookEntity = modelMapper.map(book,BookEntity.class);
        return modelMapper.map(bookRepository.save(bookEntity),Book.class);
    }

    public Book create(Book book){
        return save(book);
    }

    public Book update(String id, Book book){
        if(!existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Record not found");
        book.setId(id);
        return save(book);
    }

    public ResponseType delete(String id){
        if(!existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Record not found");
        bookRepository.deleteById(id);
        return ResponseType.SUCCESS;
    }

    public Book findById(String id){
        return bookRepository.findById(id).map(entity ->
                modelMapper.map(entity, Book.class)).orElse(null);
    }

    public List<Book> findAll(){
        return bookRepository.findAll().stream().map(entity ->
                modelMapper.map(entity, Book.class)).toList();
    }

    public boolean existsById(String id){
        return bookRepository.existsById(id);
    }
}
