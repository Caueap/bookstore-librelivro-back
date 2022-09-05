package com.api2.bookstore.repositories;

import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    public boolean existsByName(String name);


}
