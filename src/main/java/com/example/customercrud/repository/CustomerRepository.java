
package com.example.customercrud.repository;

import com.example.customercrud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    /*Optional<Customer> findByEmail(String email);*/
    // Add custom queries if needed
}
