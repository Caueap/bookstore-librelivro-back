package com.api2.bookstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "TB_BOOK")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @Column
    private String bookStatus;


    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private int rentedAmount;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private PublisherModel publisherModel;


}
