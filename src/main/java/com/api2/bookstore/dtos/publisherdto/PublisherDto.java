package com.api2.bookstore.dtos.publisherdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String city;

//    private int registeredBooksAmount;

}
