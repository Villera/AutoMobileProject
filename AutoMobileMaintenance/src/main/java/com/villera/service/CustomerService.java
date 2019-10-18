package com.villera.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.villera.model.CustomerModel;
import com.villera.repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerrepo;
	
	public CustomerModel getCustomerById(Integer cusId) {
		CustomerModel cus = customerrepo.findById(cusId).get();
		return cus;
	}
	
	public List<CustomerModel> getAllCustomers(){
		List<CustomerModel> cuslist=new ArrayList<>();
		customerrepo.findAll().forEach(e->cuslist.add(e));
		return cuslist;
	}
	public CustomerModel addCustomer(CustomerModel cus){
		CustomerModel cuslist =customerrepo.save(cus);
		return cuslist;
	}
	public void updateCustomer(CustomerModel cus) {
		customerrepo.save(cus);
	}
	
}
