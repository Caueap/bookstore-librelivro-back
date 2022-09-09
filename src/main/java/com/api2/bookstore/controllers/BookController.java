package com.api2.bookstore.controllers;

import com.api2.bookstore.dtos.bookdto.BookRequestDto;
import com.api2.bookstore.dtos.bookdto.BookResponseDto;
import com.api2.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api2/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto create(@RequestBody @Valid BookRequestDto bookRequestDto) {
        return bookService.create(bookRequestDto);
    }

    @GetMapping("/{id}")
    public BookResponseDto getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @GetMapping
    public Page<BookResponseDto> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDto update(@PathVariable Long id, @RequestBody @Valid BookRequestDto bookToUpdtateDto) {
        return bookService.update(id, bookToUpdtateDto);
    }

    //Código de Henrique
    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestBody @Valid BookRequestDto bookRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookRequestDto));
    }*/

    //Código da Michelle Brito
    /*final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Object> saveBook(@RequestBody @Valid BookDto bookDto) {
        if(bookService.existsByName(bookDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Book already exists");
        }
        var bookModel = new BookModel();
        BeanUtils.copyProperties(bookDto, bookModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookModel));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") UUID id) {
        Optional<BookModel> bookModelOptional = bookService.findById(id);
        if (!bookModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") UUID id) {
        Optional<BookModel> bookModelOptional = bookService.findById(id);
        if (!bookModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        bookService.delete(bookModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePublisher(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid BookDto bookDto) {
        Optional<BookModel> bookModelOptional = bookService.findById(id);
        if (!bookModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        var bookModel = new BookModel();
        BeanUtils.copyProperties(bookDto, bookModel);
        bookModel.setId(bookModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(bookModel));
    }*/


}
