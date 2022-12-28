package com.api2.bookstore.exception.bookexception;

import com.api2.bookstore.models.BookModel;

import javax.persistence.PersistenceException;

public class RentedBookException extends PersistenceException {
    public RentedBookException(BookModel foundBookModel) {
        super(String.format("Este livro não pode ser editado (alugado)"));
    }
}
