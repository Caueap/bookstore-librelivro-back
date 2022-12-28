package com.api2.bookstore.dtos.rentaldto;

import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.ClientModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDeliveryDto {

    private Long id;

    private LocalDate rentalDate;

    private LocalDate expectedDeliveryDate;

    private LocalDate deliveryDate;

//    private String status;

//    private String delayStatus;

    private BookModel bookModel;

    private ClientModel clientModel;


}
