package com.api2.bookstore.repositories;

import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.ClientModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    public boolean existsByName(String name);

    @Query("SELECT b FROM BookModel b ORDER BY b.rentedAmount DESC")
    List<BookModel> findMostRented();

    @Query("SELECT b from BookModel b where b.amount > 0")
    List<BookModel> findAvalilableBooks();




}
