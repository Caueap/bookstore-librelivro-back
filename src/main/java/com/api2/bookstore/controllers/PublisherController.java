package com.api2.bookstore.controllers;

import com.api2.bookstore.dtos.PublisherDto;
import com.api2.bookstore.models.PublisherModel;
import com.api2.bookstore.services.PublisherService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api2/publisher")
public class PublisherController {

    final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<Object> savePublisher(@RequestBody @Valid PublisherDto publisherDto) {
        if(publisherService.existsByName(publisherDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Publisher already exists");
        }
        var publisherModel = new PublisherModel();
        BeanUtils.copyProperties(publisherDto, publisherModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.save(publisherModel));
    }

    @GetMapping
    public ResponseEntity<List<PublisherModel>> getAllPublishers() {
        return ResponseEntity.status(HttpStatus.OK).body(publisherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> GetOnePublisher(@PathVariable(value = "id") UUID id) {
        Optional<PublisherModel> publisherModelOptional = publisherService.findById(id);
        if (!publisherModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(publisherModelOptional.get());







    }
}
