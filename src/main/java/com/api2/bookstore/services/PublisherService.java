package com.api2.bookstore.services;

import com.api2.bookstore.models.PublisherModel;
import com.api2.bookstore.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PublisherService {

    final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public PublisherModel save(PublisherModel publisherModel) {
        return publisherRepository.save(publisherModel);
    }

    public boolean existsByName(String name) {
        return publisherRepository.existsByName(name);
    }
}
