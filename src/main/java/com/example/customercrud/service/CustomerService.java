
package com.example.customercrud.service;
import com.example.customercrud.dtos.CustomerDto;
import com.example.customercrud.model.Customer;
import com.example.customercrud.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto , String customerId);

    void delete(String customerId);

    List<CustomerDto> getAll();

    CustomerDto getSingle(String customerId);


}

