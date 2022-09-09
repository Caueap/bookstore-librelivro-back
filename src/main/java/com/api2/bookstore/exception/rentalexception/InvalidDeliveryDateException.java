package com.api2.bookstore.exception.rentalexception;

import com.api2.bookstore.dtos.rentaldto.RentalReqDelDto;
import com.api2.bookstore.dtos.rentaldto.RentalRequestDto;

import javax.persistence.PersistenceException;

public class InvalidDeliveryDateException extends PersistenceException {
    public InvalidDeliveryDateException(RentalReqDelDto rentalReqDelDto) {
        super(String.format("Invalid delivery Date", rentalReqDelDto));
    }

}
