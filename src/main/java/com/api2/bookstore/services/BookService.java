package com.api2.bookstore.services;

import com.api2.bookstore.dtos.bookdto.BookRequestDto;
import com.api2.bookstore.dtos.bookdto.BookResponseDto;
import com.api2.bookstore.exception.bookexception.BookNotFoundException;
import com.api2.bookstore.exception.bookexception.RentedBookException;
import com.api2.bookstore.mappers.BookMapper;
import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.PublisherModel;
import com.api2.bookstore.repositories.BookRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

//    private final static BookMapper bookMapper = BookMapper.INSTANCE;

    private BookRepository bookRepository;

    private PublisherService publisherService;

    private ModelMapper mapper;



    @Autowired
    public BookService(BookRepository bookRepository, PublisherService publisherService, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.publisherService = publisherService;
        this.mapper = mapper;

    }

    public BookResponseDto create(@NotNull BookRequestDto bookRequestDto) {
        PublisherModel foundPublisher = publisherService.verifyAndGetIfExists(bookRequestDto.getPublisherModelId());
        BookModel bookToSave = mapper.map(bookRequestDto, BookModel.class);
        bookToSave.setPublisherModel(foundPublisher);
        BookModel savedBook = bookRepository.save(bookToSave);
        BookResponseDto savedBookDto = mapper.map(savedBook, BookResponseDto.class);
        return savedBookDto;

    }

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookModel -> mapper.map(BookModel, BookResponseDto.class))
                .collect(Collectors.toList());
    }

        public BookResponseDto getById(Long id) {
        BookModel foundBookModel = verifyAndGetIfExists(id);
        BookResponseDto foundBookModelDto = mapper.map(foundBookModel, BookResponseDto.class);
        return foundBookModelDto;
    }

    public void delete(Long id) {
        verifyAndGetIfExists(id);
        bookRepository.deleteById(id);
    }

    public BookResponseDto update(Long id, BookRequestDto bookToUpdateDto) {
        PublisherModel foundPublisher = publisherService.verifyAndGetIfExists(bookToUpdateDto.getPublisherModelId());
        BookModel foundBookModel = verifyAndGetIfExists(id);
        verifyUpdatePossibility(foundBookModel);

        bookToUpdateDto.setId(foundBookModel.getId());
        BookModel bookToUpdate = mapper.map(bookToUpdateDto, BookModel.class);
        bookToUpdate.setPublisherModel(foundPublisher);
        BookModel updatedBook = bookRepository.save(bookToUpdate);
        BookResponseDto updatedBookDto = mapper.map(updatedBook, BookResponseDto.class);
        return updatedBookDto;

    }

    private void verifyUpdatePossibility(BookModel foundBookModel) {
        if (foundBookModel.getBookStatus().equals("Alugado")) {
            throw new RentedBookException(foundBookModel);
        }
    }


    public BookModel verifyAndGetIfExists(Long id) {
        BookModel foundBookModel = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return foundBookModel;
    }


//---- Código da Udemy -----------------------------------------------------------------------------------------------------------------

//    public BookResponseDto create(@NotNull BookRequestDto bookRequestDto) {
//        PublisherModel foundPublisher = publisherService.verifyAndGetIfExists(bookRequestDto.getPublisherModelId());
//        BookModel bookToSave = bookMapper.toModel(bookRequestDto);
//        bookToSave.setPublisherModel(foundPublisher);
//        BookModel savedBook = bookRepository.save(bookToSave);
//        return bookMapper.toDTO(savedBook);
//    }

//    public BookResponseDto getById(Long id) {
//        BookModel foundBookModel = verifyAndGetIfExists(id);
//        return bookMapper.toDTO(foundBookModel);
//    }
//
//    public List<BookResponseDto> getAllBooks() {
//        return bookRepository.findAll()
//                .stream()
//                .map(bookMapper::toDTO)
//                .collect(Collectors.toList());
//
//
//    }
//
//    public void delete(Long id) {
//        verifyAndGetIfExists(id);
//        bookRepository.deleteById(id);
//    }

//          public BookResponseDto update(Long id, BookRequestDto bookToUpdateDto) {
//        PublisherModel foundPublisher = publisherService.verifyAndGetIfExists(bookToUpdateDto.getPublisherModelId());
//        BookModel foundBookModel = verifyAndGetIfExists(id);
//        bookToUpdateDto.setId(foundBookModel.getId());
//        BookModel bookToUpdate = bookMapper.toModel(bookToUpdateDto);
//        bookToUpdate.setPublisherModel(foundPublisher);
//        BookModel updatedBook = bookRepository.save(bookToUpdate);
//        return bookMapper.toDTO(updatedBook);
//    }



//---- Código da Michelle -----------------------------------------------------------------------------------------------------------------




    /*public boolean existsByName(String name) {
        return bookRepository.existsByName(name);
    }

    @Transactional
    public BookModel save(BookModel bookModel) {
        return bookRepository.save(bookModel);
    }

    public List<BookModel> findAll() {
        return bookRepository.findAll();
    }

    public Optional<BookModel> findById(UUID id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public void delete(BookModel bookModel) {
        bookRepository.delete(bookModel);
    }*/
}
