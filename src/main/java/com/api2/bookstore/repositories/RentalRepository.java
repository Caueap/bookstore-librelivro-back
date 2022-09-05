package com.api2.bookstore.repositories;

import com.api2.bookstore.models.RentalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<RentalModel, Long> {
}
