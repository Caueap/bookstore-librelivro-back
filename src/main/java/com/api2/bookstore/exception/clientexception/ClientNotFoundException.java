package com.api2.bookstore.exception.clientexception;

import javax.persistence.EntityNotFoundException;

public class ClientNotFoundException extends EntityNotFoundException {
    public ClientNotFoundException(Long id) {
        super(String.format("User with id %s does not exist", id));
    }

    public ClientNotFoundException(String name) {
        super(String.format("User with name %s does not exist", name));
    }
}
