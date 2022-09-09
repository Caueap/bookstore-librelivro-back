package com.api2.bookstore.exception.rentalexception;

import com.api2.bookstore.dtos.rentaldto.RentalRequestDto;

import javax.persistence.PersistenceException;

public class InvalidExpectedDeliveryDateException extends PersistenceException {
    public InvalidExpectedDeliveryDateException(RentalRequestDto rentalRequestDto) {
        super(String.format("Invalid expected delivery date"));
    }
}
