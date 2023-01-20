package com.api2.bookstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity(name = "TB_RENTAL")
public class RentalModel {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private LocalDate rentalDate;


    @Column
    private LocalDate expectedDeliveryDate;

    @Column
    private LocalDate deliveryDate;

    @Column
    private String status;

//    @JsonFormat
//    private String delayStatus;

//    @Column
//    private Long value;


    @ManyToOne(cascade = {CascadeType.MERGE})
    private BookModel bookModel;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private ClientModel clientModel;


}

