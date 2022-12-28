package com.api2.bookstore.mappers;

import com.api2.bookstore.dtos.bookdto.BookRequestDto;
import com.api2.bookstore.models.BookModel;

public class BookMapperM {

    public BookModel toEntity(BookRequestDto bookRequestDto) {
        BookModel bookModel = new BookModel();

        bookModel.setId(bookRequestDto.getId());
        bookModel.setName(bookRequestDto.getName());
        bookModel.setAuthor(bookRequestDto.getAuthor());
        bookModel.setReleaseDate(bookRequestDto.getReleaseDate());
        bookModel.setIsbn(bookRequestDto.getIsbn());
        bookModel.setAmount(bookRequestDto.getAmount());

        return bookModel;
    }
}
