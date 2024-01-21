package com.example.customercrud.repository;

/*import com.example.customercrud.model.Customer;*/
import com.example.customercrud.model.Customer;
import com.example.customercrud.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
   Optional <Login> findByEmail(String email);
}

