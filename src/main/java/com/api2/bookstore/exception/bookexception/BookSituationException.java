package com.api2.bookstore.exception.bookexception;

import javax.persistence.PersistenceException;

public class BookSituationException extends PersistenceException {
    public BookSituationException() {
        super(String.format("O livro não pode ser " +
                "editado, pois está cadastrado em um aluguel."));

    }
}
