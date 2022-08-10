package com.api2.bookstore.services;

import com.api2.bookstore.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
