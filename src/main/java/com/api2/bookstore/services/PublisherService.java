package com.api2.bookstore.services;

import com.api2.bookstore.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

    final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }
}
