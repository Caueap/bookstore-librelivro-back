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
public class RentalResponseDto {


    private Long id;

    private LocalDate rentalDate;

    private LocalDate expectedDeliveryDate;

    private LocalDate deliveryDate;

    private BookModel bookModel;

    private ClientModel clientModel;
}
