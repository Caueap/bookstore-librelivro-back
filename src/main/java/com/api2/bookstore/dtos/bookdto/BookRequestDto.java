package com.api2.bookstore.dtos.bookdto;

import com.api2.bookstore.models.BookModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String author;

    @NotNull
//    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

//    private String bookStatus;

    @NotNull
    private int amount;

    @NotNull
    private Long publisherModelId;


}
