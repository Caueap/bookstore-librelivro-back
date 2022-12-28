package com.api2.bookstore.exception.bookexception;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundException extends EntityNotFoundException {
    public BookNotFoundException(Long id) {
        super(String.format("Book with id %s does not exist", id));
    }


}
