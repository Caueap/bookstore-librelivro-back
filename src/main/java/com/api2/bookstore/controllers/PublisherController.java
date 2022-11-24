package com.api2.bookstore.controllers;

import com.api2.bookstore.dtos.publisherdto.PublisherDto;
import com.api2.bookstore.services.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api2/publisher")
public class PublisherController {

    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublisherDto create(@RequestBody @Valid PublisherDto publisherDto)  {
        return publisherService.create(publisherDto);
    }

    @GetMapping("/{id}")
    //Não precisa do ResponseStatus pq o get, por padrão, já retorna um código 200 ok
    public PublisherDto findById(@PathVariable Long id) {
        return publisherService.getById(id);
    }

    @GetMapping
    //Novamente, não precisa do ResponseStatus pq o get, por padrão, já retorna um código 200 ok
    public List<PublisherDto> findAll() {
        return publisherService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        publisherService.delete(id);
    }

    @PutMapping("/{id}")
    public PublisherDto update(@PathVariable Long id, @RequestBody @Valid PublisherDto publisherToUpdateDto) {
        return publisherService.update(id, publisherToUpdateDto);
    }

    /*@PostMapping
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
    public ResponseEntity<Object> getOnePublisher(@PathVariable(value = "id") UUID id) {
        Optional<PublisherModel> publisherModelOptional = publisherService.findById(id);
        if (!publisherModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(publisherModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePublisher(@PathVariable(value = "id") UUID id) {
        Optional<PublisherModel> publisherModelOptional = publisherService.findById(id);
        if (!publisherModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }
        publisherService.delete(publisherModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Publisher deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePublisher(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid PublisherDto publisherDto) {
        Optional<PublisherModel> publisherModelOptional = publisherService.findById(id);
        if (!publisherModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found");
        }
        var publisherModel = new PublisherModel();
        BeanUtils.copyProperties(publisherDto, publisherModel);
        publisherModel.setId(publisherModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(publisherService.save(publisherModel));
    }*/


}
