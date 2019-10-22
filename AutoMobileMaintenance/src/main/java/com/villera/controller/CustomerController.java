package com.villera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.villera.model.CustomerModel;
import com.villera.service.CustomerService;

@RestController
@RequestMapping(value="/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
    
    @PutMapping("/update-customer/{customerid}")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerModel customer,
      @PathVariable("customerid") Integer customerid) {
        customerservice.updateCustomer(customer);
        return ResponseEntity.ok("Customer Updated");
    }
    
}
