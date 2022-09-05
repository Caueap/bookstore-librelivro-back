package com.api2.bookstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity(name = "TB_RENTAL")
public class RentalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate rentalDate;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate expectedDeliveryDate;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate deliveryDate;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private BookModel bookModel;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private ClientModel clientModel;
}
