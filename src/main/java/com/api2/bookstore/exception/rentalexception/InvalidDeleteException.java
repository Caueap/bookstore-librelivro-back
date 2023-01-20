package com.api2.bookstore.exception.rentalexception;

import com.api2.bookstore.dtos.rentaldto.RentalReqDelDto;

import javax.persistence.PersistenceException;

public class InvalidDeleteException extends PersistenceException {
    public InvalidDeleteException() {
        super(String.format("Este livro ainda não foi devolvido"));
    }
}
