package com.villera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.villera.model.CustomerModel;
import com.villera.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;

	@GetMapping("customer/{customerid}")
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("customerid") Integer customerid){
		CustomerModel cus=customerservice.getCustomerById(customerid);
		return new ResponseEntity<CustomerModel>(cus,HttpStatus.OK);
	}
	
	@GetMapping("customers")
	public ResponseEntity<List<CustomerModel>> getAllCustomers(){
		List<CustomerModel> cuslist=customerservice.getAllCustomers();
		return new ResponseEntity<List<CustomerModel>>(cuslist,HttpStatus.OK);
	}
	
	@PostMapping("customer")
	public ResponseEntity<Void> addCustomer(@RequestBody CustomerModel cus, UriComponentsBuilder builder){
		CustomerModel flag=customerservice.addCustomer(cus);
		if(flag==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders header=new HttpHeaders();
		header.setLocation(builder.path("/customer/{customerid}").buildAndExpand(cus.getCustomerid()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
	}
}
