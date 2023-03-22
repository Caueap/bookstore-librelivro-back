package com.api2.bookstore.services;

import com.api2.bookstore.dtos.publisherdto.PublisherDto;
import com.api2.bookstore.exception.publexception.PublisherAlreadyExistsException;
import com.api2.bookstore.exception.publexception.PublisherNotFoundException;
import com.api2.bookstore.models.PublisherModel;
import com.api2.bookstore.repositories.PublisherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {

//    private final static PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    private PublisherRepository publisherRepository;

    private ModelMapper mapper;


    @Autowired
    public PublisherService(PublisherRepository publisherRepository, ModelMapper mapper) {
        this.publisherRepository = publisherRepository;
        this.mapper = mapper;

    }

    public PublisherDto create(PublisherDto publisherDto) {
        verifyIfExists(publisherDto.getName());
        PublisherModel publisherModelToCreate = mapper.map(publisherDto, PublisherModel.class);
        PublisherModel createdPublisherModel = publisherRepository.save(publisherModelToCreate);
        PublisherDto createdPublisherModelDto = mapper.map(createdPublisherModel, PublisherDto.class);
        return createdPublisherModelDto;
    }

    public PublisherDto getById(Long id) {
        PublisherModel foundPublisherModel = verifyAndGetPublisher(id);
        PublisherDto foundPublisherModelDto = mapper.map(foundPublisherModel, PublisherDto.class);
        return foundPublisherModelDto;
    }

    public List<PublisherDto> getAll() {
        return publisherRepository.findAll()
                .stream()
                .map(PublisherModel -> mapper.map(PublisherModel, PublisherDto.class))
                .collect(Collectors.toList());
    }

    public List<PublisherDto> getPublisherWithMoreBooks() {
        return publisherRepository.findPublisherWithMoreBooks()
                .stream()
                .map(PublisherModel -> mapper.map(PublisherModel, PublisherDto.class))
                .collect(Collectors.toList());
    }



    public void delete(Long id) {
        verifyAndGetPublisher(id);
        publisherRepository.deleteById(id);
    }

    public PublisherDto update(Long id, PublisherDto publisherToUpdateDto) {
        PublisherModel foundPublisher = verifyAndGetIfExists(id);
        publisherToUpdateDto.setId(foundPublisher.getId());
        PublisherModel publisherToUpdate = mapper.map(publisherToUpdateDto, PublisherModel.class);
        PublisherModel updatedPublisher = publisherRepository.save(publisherToUpdate);
        PublisherDto updatedPublisherDto = mapper.map(updatedPublisher, PublisherDto.class);
        return updatedPublisherDto;
    }


    private void verifyIfExists(String publisherModelName) {
        publisherRepository.findByName(publisherModelName)
                .ifPresent(publisherModel -> {throw new PublisherAlreadyExistsException(publisherModelName);});
    }

    private PublisherModel verifyAndGetPublisher(Long id) {
        PublisherModel foundPublisherModel = publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
        return foundPublisherModel;
    }

    public PublisherModel verifyAndGetIfExists(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

//---- Código da Udemy ----------------------------------------------------------------------------------

//        public PublisherDto create(PublisherDto publisherDto)  {
//        verifyIfExists(publisherDto.getName());
//        PublisherModel publisherModelToCreate = publisherMapper.toModel(publisherDto); /*publisherDto sendo convertido
//        em model e sendo armazenado em publisherModelToCreate*/
//        PublisherModel createdPublisherModel = publisherRepository.save(publisherModelToCreate);
//        return publisherMapper.toDTO(createdPublisherModel);
//    }

//    public PublisherDto getById(Long id) {
//        PublisherModel foundPublisherModel = verifyAndGetPublisher(id);
//        return publisherMapper.toDTO(foundPublisherModel);
//    }
//
//    public List<PublisherDto> getAll() {
//        return publisherRepository.findAll()
//                .stream()
//                .map(publisherMapper::toDTO))
//                .collect(Collectors.toList());
//
//    }




//        public PublisherDto update(Long id, PublisherDto publisherToUpdateDto) {
//        PublisherModel foundPublisher = verifyAndGetIfExists(id);
//        publisherToUpdateDto.setId(foundPublisher.getId());
//        PublisherModel publisherToUpdate = publisherMapper.toModel(publisherToUpdateDto);
//        PublisherModel updatedPublisher = publisherRepository.save(publisherToUpdate);
//        return publisherMapper.toDTO(updatedPublisher);
//    }




//---- Código do Henrique ---------------------------------------------------------------------------
    /*public PublisherModel verifyAndGetIfExists(Long id) {
        Optional<PublisherModel> publisherModelFind = publisherRepository.findById(id);

        PublisherModel publisherModel;

        publisherModel = publisherModelFind.get();
        return publisherModel;
    }*/


//---- Código da Michelle -------------------------------------------------------------------------------

    /*@Transactional
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

    @Transactional
    public void delete(PublisherModel publisherModel) {
        publisherRepository.delete(publisherModel);
    }*/
}
