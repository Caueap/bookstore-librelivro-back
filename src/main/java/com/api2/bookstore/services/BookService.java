package com.api2.bookstore.services;

import com.api2.bookstore.dtos.bookdto.BookRequestDto;
import com.api2.bookstore.dtos.bookdto.BookResponseDto;
import com.api2.bookstore.exception.bookexception.BookNotFoundException;
import com.api2.bookstore.mappers.BookMapper;
import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.PublisherModel;
import com.api2.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final static BookMapper bookMapper = BookMapper.INSTANCE;

    private BookRepository bookRepository;

    private PublisherService publisherService;


    @Autowired
    public BookService(BookRepository bookRepository, PublisherService publisherService) {
        this.bookRepository = bookRepository;
        this.publisherService = publisherService;

    }

    public BookResponseDto create(@NotNull BookRequestDto bookRequestDto) {
        PublisherModel foundPublisher = publisherService.verifyAndGetIfExists(bookRequestDto.getPublisherModelId());
        BookModel bookToSave = bookMapper.toModel(bookRequestDto);
        bookToSave.setPublisherModel(foundPublisher);
        BookModel savedBook = bookRepository.save(bookToSave);
        return bookMapper.toDTO(savedBook);
    }

    public BookResponseDto getById(Long id) {
        BookModel foundBookModel = verifyAndGetIfExists(id);
        return bookMapper.toDTO(foundBookModel);
    }

    public Page<BookResponseDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDTO);

    }

    public void delete(Long id) {
        verifyAndGetIfExists(id);
        bookRepository.deleteById(id);
    }

    public BookResponseDto update(Long id, BookRequestDto bookToUpdtateDto) {
        BookModel foundBookModel = verifyAndGetIfExists(id);
        bookToUpdtateDto.setId(foundBookModel.getId());
        BookModel bookToUpdate = bookMapper.toModel(bookToUpdtateDto);
        BookModel updatedBook = bookRepository.save(bookToUpdate);
        return bookMapper.toDTO(updatedBook);
    }

    public BookModel verifyAndGetIfExists(Long id) {
        BookModel foundBookModel = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return foundBookModel;
    }






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
