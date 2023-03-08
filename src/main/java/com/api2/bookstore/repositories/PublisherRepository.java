package com.api2.bookstore.repositories;

import com.api2.bookstore.models.BookModel;
import com.api2.bookstore.models.PublisherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<PublisherModel, Long> {

    Optional<PublisherModel> findByName(String name);

    /*public boolean existsByName(String name);*/

    @Query("SELECT p FROM PublisherModel p ORDER BY b.rentedAmount DESC")
    List<PublisherModel> findPublisherWithMoreBooks();

}
