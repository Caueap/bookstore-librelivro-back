package com.api2.bookstore.dtos.bookdto;

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
public class BookRequestDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String author;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @NotBlank
    private String isbn;

    @NotNull
    private Long publisherModelId;

}
