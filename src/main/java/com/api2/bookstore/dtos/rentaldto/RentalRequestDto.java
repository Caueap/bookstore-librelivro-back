package com.api2.bookstore.dtos.rentaldto;

import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.ClientModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequestDto {


    private Long id;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate rentalDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate expectedDeliveryDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
//    private LocalDate deliveryDate;

//    private String status;

//    private String delayStatus;

    @NotNull
    private Long bookModelId;

    @NotNull
    private Long clientModelId;
}
