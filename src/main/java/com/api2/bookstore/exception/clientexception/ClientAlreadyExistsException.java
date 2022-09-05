package com.api2.bookstore.exception.clientexception;

import javax.persistence.EntityExistsException;

public class ClientAlreadyExistsException extends EntityExistsException {
    public ClientAlreadyExistsException(String email) {
        super(String.format("Client with email %s already exists", email));

    }
}
