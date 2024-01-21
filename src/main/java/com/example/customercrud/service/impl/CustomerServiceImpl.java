
package com.example.customercrud.service.impl;

import com.example.customercrud.dtos.CustomerDto;
import com.example.customercrud.model.Customer;
import com.example.customercrud.repository.CustomerRepository;
import com.example.customercrud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        // Implement create customer logic

        String customerId = UUID.randomUUID().toString();
        customerDto.setCustomerId(customerId);

        //dto-entity

        Customer customer = dtoToEntity(customerDto);
        Customer saveCustomer= customerRepository.save(customer);
        CustomerDto newDto= entityToDto(saveCustomer);
        return newDto;


    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, String customerId) {
        Customer customer= customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("customer id  is not found"));
          customer.setAddress(customerDto.getAddress());
          customer.setCity(customerDto.getCity());
          customer.setEmail(customerDto.getEmail());
          customer.setPhone(customerDto.getPhone());
          customer.setStreet(customerDto.getStreet());
          customer.setState(customerDto.getState());
          customer.setFirstName(customerDto.getFirstName());
          customer.setLastName(customerDto.getLastName());
          Customer customerUpdate = customerRepository.save(customer);
          CustomerDto customerDto1 = entityToDto(customer);
          return customerDto1;
    }

    @Override
    public void delete(String customerId) {
        Customer customer= customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("customer id  is not found"));
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers= customerRepository.findAll();
        List<CustomerDto> dtoList = customers.stream().map(customer -> entityToDto(customer)).collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public CustomerDto getSingle(String customerId) {
        Customer customer= customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("customer id  is not found"));

        return entityToDto(customer);
    }


    private CustomerDto entityToDto(Customer saveCustomer){
        CustomerDto customerDto= CustomerDto.builder()
                .customerId(saveCustomer.getCustomerId())
                .firstName(saveCustomer.getFirstName())
                .lastName(saveCustomer.getLastName())
                .phone(saveCustomer.getPhone())
                .street(saveCustomer.getStreet())
                .address(saveCustomer.getAddress())
                .city(saveCustomer.getCity())
                .state(saveCustomer.getState())
                .email(saveCustomer.getEmail()).build();

        return customerDto;
    }

    private Customer dtoToEntity(CustomerDto customerDto){
        Customer customer= Customer.builder()
                .customerId(customerDto.getCustomerId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .phone(customerDto.getPhone())
                .street(customerDto.getStreet())
                .address(customerDto.getAddress())
                .city(customerDto.getCity())
                .state(customerDto.getState())
                .email(customerDto.getEmail()).build();

        return customer;
    }


}
