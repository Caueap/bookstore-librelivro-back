package com.api2.bookstore.exception.publexception;

import javax.persistence.EntityExistsException;

public class PublisherAlreadyExistsException extends EntityExistsException {
    public PublisherAlreadyExistsException(String name) {
        super(String.format("User with name %s already exists", name));
    }
}
