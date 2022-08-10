package com.api2.bookstore.repositories;

import com.api2.bookstore.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {

    boolean existsByEmail(String email);


}
