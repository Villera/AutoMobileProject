package com.villera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.villera.model.CustomerModel;
import com.villera.repository.CustomerRepoJPA;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {

	@Autowired
	CustomerRepoJPA customerrepojpa;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		CustomerModel customer = customerrepojpa.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		return CustomerPrinciple.build(customer);
	}
}