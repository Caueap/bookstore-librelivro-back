package com.api2.bookstore.services;

import com.api2.bookstore.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


}
