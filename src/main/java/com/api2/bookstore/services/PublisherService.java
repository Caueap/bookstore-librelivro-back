package com.api2.bookstore.services;

import com.api2.bookstore.models.PublisherModel;
import com.api2.bookstore.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<PublisherModel> findAll() {
        return publisherRepository.findAll();
    }

    public Optional<PublisherModel> findById(UUID id) {
        return publisherRepository.findById(id);
    }
}
