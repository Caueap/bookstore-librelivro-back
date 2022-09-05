package com.api2.bookstore.exception.rentalexception;

import javax.persistence.EntityNotFoundException;

public class RentalNotFoundException extends EntityNotFoundException {
    public RentalNotFoundException(Long id) {
        super(String.format("Rental with id %s does not exist", id));
    }
}
