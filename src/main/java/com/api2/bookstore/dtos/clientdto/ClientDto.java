package com.api2.bookstore.dtos.clientdto;

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
public class ClientDto {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private int age;

    @NotBlank
    private String email;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

}
