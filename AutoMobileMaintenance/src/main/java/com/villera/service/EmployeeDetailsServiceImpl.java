package com.villera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.villera.model.EmployeeModel;
import com.villera.repository.EmployeeRepoJPA;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

	@Autowired
	EmployeeRepoJPA employeerepojpa;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		EmployeeModel emp = employeerepojpa.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		return EmployeePrinciple.build(emp);
	}
}
