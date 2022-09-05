package com.api2.bookstore.services;

import com.api2.bookstore.dtos.clientdto.ClientDto;
import com.api2.bookstore.dtos.clientdto.ClientMessageDto;
import com.api2.bookstore.dtos.publisherdto.PublisherDto;
import com.api2.bookstore.exception.clientexception.ClientAlreadyExistsException;
import com.api2.bookstore.exception.clientexception.ClientNotFoundException;
import com.api2.bookstore.exception.publexception.PublisherNotFoundException;
import com.api2.bookstore.mappers.ClientMapper;
import com.api2.bookstore.models.ClientModel;
import com.api2.bookstore.models.PublisherModel;
import com.api2.bookstore.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final static ClientMapper clientMapper = ClientMapper.INSTANCE;

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientMessageDto create(ClientDto clientToCreateDto) {
        verifyIfExistsEmail(clientToCreateDto.getEmail());
        ClientModel clientToCreate = clientMapper.toModel(clientToCreateDto);
        ClientModel createdClient = clientRepository.save(clientToCreate);
        return creationMessage(createdClient);
    }

    public List<ClientDto> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClientDto getById(Long id) {
        ClientModel foundClientModel = verifyAndGetClient(id);
        return clientMapper.toDTO(foundClientModel);

    }

    public ClientMessageDto update(Long id, ClientDto clientToUpdateDto) {
        ClientModel foundClient = verifyAndGetIfExists(id);
        clientToUpdateDto.setId(foundClient.getId());
        ClientModel clientToUpdate = clientMapper.toModel(clientToUpdateDto);
        ClientModel updatedClient = clientRepository.save(clientToUpdate);
        return updateMessage(updatedClient);
    }

    public void delete(Long id) {
        verifyAndGetIfExists(id);
        clientRepository.deleteById(id);
    }

    public ClientModel verifyAndGetIfExists(Long id) {
        return clientRepository.findById(id)
                        .orElseThrow(() -> new ClientNotFoundException(id));
    }

    private void verifyIfExistsEmail(String email) {
        Optional<ClientModel> foundClient = clientRepository.findByEmail(email);
        if (foundClient.isPresent()) {
            throw new ClientAlreadyExistsException(email);
        }
    }

    private ClientMessageDto creationMessage(ClientModel createdClient) {
        String createdClientName = createdClient.getName();
        Long createdClientId = createdClient.getId();
        String createdClientMessage = String.format("User %s with ID %s successfully created", createdClientName, createdClientId);
        return ClientMessageDto
                .builder()
                .message(createdClientMessage)
                .build();
    }

    private ClientMessageDto updateMessage(ClientModel updatedClient) {
        String createdClientName = updatedClient.getName();
        Long createdClientId = updatedClient.getId();
        String createdClientMessage = String.format("User %s with ID %s successfully updated", createdClientName, createdClientId);
        return ClientMessageDto
                .builder()
                .message(createdClientMessage)
                .build();
    }


    private ClientModel verifyAndGetClient(Long id) {
        ClientModel foundClientModel = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        return foundClientModel;
    }

    public ClientModel verifyAndGetClientIfExists(String name) {
        return clientRepository.findByName(name)
                .orElseThrow(() -> new ClientNotFoundException(name));
    }




    /*@Transactional
    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public Optional<ClientModel> findById(UUID id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }

    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }*/

}
