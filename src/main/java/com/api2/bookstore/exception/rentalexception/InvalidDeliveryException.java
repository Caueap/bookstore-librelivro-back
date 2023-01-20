package com.api2.bookstore.exception.rentalexception;

import javax.persistence.PersistenceException;

public class InvalidDeliveryException extends PersistenceException {
    public InvalidDeliveryException() {
        super(String.format("Este aluguel já foi atualizado"));
    }
}
