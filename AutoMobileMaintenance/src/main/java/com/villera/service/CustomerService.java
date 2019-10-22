package com.villera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.villera.model.CustomerModel;
import com.villera.repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerrepo;
	
	public void updateCustomer(CustomerModel cus) {
		customerrepo.save(cus);
	}
	
	/*Customer cus = customerrepo.findOne(id);
	cus.setFirstName("new first name");
	cus.setLastName("new last name");*/
}
