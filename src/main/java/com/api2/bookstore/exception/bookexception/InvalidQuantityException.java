package com.api2.bookstore.exception.bookexception;

import com.api2.bookstore.models.BookModel;

import javax.persistence.PersistenceException;

public class InvalidQuantityException extends PersistenceException {
    public InvalidQuantityException() {
        super(String.format("Todas as unidades deste livro estão alugadas"));
    }
}
