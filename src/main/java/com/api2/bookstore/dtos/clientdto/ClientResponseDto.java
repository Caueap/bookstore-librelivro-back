package com.api2.bookstore.dtos.clientdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {

    private Long id;

    private String name;

//    private int age;

    private String email;

    private String city;

    private String address;

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private LocalDate birthDate;


}
