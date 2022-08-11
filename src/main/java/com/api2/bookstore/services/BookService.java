package com.api2.bookstore.services;

import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.repositories.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public boolean existsByName(String name) {
        return bookRepository.existsByName(name);
    }

    @Transactional
    public BookModel save(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }
}
