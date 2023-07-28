package com.api2.bookstore.dtos.rentaldto;

import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.ClientModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDeliveryDto {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate rentalDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expectedDeliveryDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate deliveryDate;

    private String status;

//    private String delayStatus;

    private BookModel bookModel;

    private ClientModel clientModel;


}
