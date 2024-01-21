
package com.example.customercrud.controller;
import com.example.customercrud.dtos.ApiResponseMessage;
import com.example.customercrud.dtos.CustomerDto;
import com.example.customercrud.model.Customer;
import com.example.customercrud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {

     @Autowired
     private CustomerService customerService;
//
    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String customerId, @RequestBody CustomerDto customerDto) {
      CustomerDto customerDto1= customerService.updateCustomer(customerDto,customerId)  ;
      return  new ResponseEntity<>(customerDto1,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
       return  new ResponseEntity<>(customerService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String customerId) {
        return new ResponseEntity<>(customerService.getSingle(customerId),HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponseMessage> deleteCustomer(@PathVariable String customerId) {
        customerService.delete(customerId);
        ApiResponseMessage message= ApiResponseMessage.builder().message("customer is deleted").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}

