package com.api2.bookstore.controllers;

import com.api2.bookstore.dtos.clientdto.ClientDto;
import com.api2.bookstore.dtos.clientdto.ClientMessageDto;
import com.api2.bookstore.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api2/client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientMessageDto create(@RequestBody @Valid ClientDto clientToCreateDto) {
        return clientService.create(clientToCreateDto);
    }

    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PutMapping("/{id}")
    public ClientMessageDto update(@PathVariable Long id, @RequestBody @Valid ClientDto clientToUpdateDto) {
        return clientService.update(id, clientToUpdateDto);
    }

    /*@PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDto clientDto) {
        if(clientService.existsByEmail(clientDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email already exists");
        }
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDto, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
    }

    @GetMapping
    public ResponseEntity<List<ClientModel>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id) {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id) {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        clientService.delete(clientModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid ClientDto clientDto) {
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDto, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModel));
    }*/
}
