package com.api2.bookstore.exception.clientexception;

import javax.persistence.PersistenceException;

public class ClientSituationException extends PersistenceException {
    public ClientSituationException() {
        super(String.format("O cliente não pode ser " +
                "editado, pois está cadastrado em um aluguel."));

    }
}
