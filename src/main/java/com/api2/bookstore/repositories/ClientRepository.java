package com.api2.bookstore.repositories;

import com.api2.bookstore.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long>{

    Optional<ClientModel> findByEmail(String email);

    Optional<ClientModel> findByName(String name);


    //boolean existsByEmail(String email);


}
