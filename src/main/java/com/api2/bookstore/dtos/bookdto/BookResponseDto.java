package com.api2.bookstore.dtos.bookdto;

import com.api2.bookstore.dtos.clientdto.ClientDto;
import com.api2.bookstore.dtos.publisherdto.PublisherDto;
import com.api2.bookstore.models.ClientModel;
import com.api2.bookstore.models.PublisherModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private Long id;

    private String name;

    private String author;

    private LocalDate releaseDate;

    private String isbn;

    private PublisherModel publisherModel;


}
