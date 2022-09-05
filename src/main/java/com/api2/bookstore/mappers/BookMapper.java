package com.api2.bookstore.mappers;

import com.api2.bookstore.dtos.bookdto.BookRequestDto;
import com.api2.bookstore.dtos.bookdto.BookResponseDto;
import com.api2.bookstore.models.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookModel toModel(BookRequestDto bookRequestDto);

    BookModel toModel(BookResponseDto bookRequestDto);

    BookResponseDto toDTO(BookModel bookModel);


}
