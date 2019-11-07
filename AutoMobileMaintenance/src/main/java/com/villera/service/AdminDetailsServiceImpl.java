package com.villera.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.villera.model.AdminModel;
import com.villera.repository.AdminRepoJPA;



@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

	@Autowired
	AdminRepoJPA adminrepojpa;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		AdminModel admin = adminrepojpa.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		return AdminPrinciple.build(admin);
	}
	
}