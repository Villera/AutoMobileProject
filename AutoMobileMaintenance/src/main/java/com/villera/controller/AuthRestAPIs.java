package com.villera.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.villera.message.request.LoginForm;
import com.villera.message.request.SignUpForm;
import com.villera.message.response.JwtResponse;
import com.villera.message.response.ResponseMessage;
import com.villera.model.CustomerModel;
import com.villera.model.Role;
import com.villera.model.RoleName;
import com.villera.repository.AdminRepoJPA;
import com.villera.repository.CustomerRepoJPA;
import com.villera.repository.EmployeeRepoJPA;
import com.villera.repository.RoleRepository;
import com.villera.security.jwt.JwtProvider;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomerRepoJPA customerrepojpa;
	
	//Autowire adminrepo
	@Autowired
	AdminRepoJPA adminrepojpa;
	
	@Autowired
	EmployeeRepoJPA employeerepojpa;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails customerDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, customerDetails.getUsername(), customerDetails.getAuthorities()));
	}
	
	//sign in just for admin
	//jwtadmin = jwtProvider.generatejWTTokenAdmin
	@PostMapping("/signin_admin")
	public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtadmin = jwtProvider.generateJwtTokenAdmin(authentication);
		UserDetails adminDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwtadmin, adminDetails.getUsername(), adminDetails.getAuthorities()));
	}
	
	@PostMapping("/signin_employee")
	public ResponseEntity<?> authenticateEmployee(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtemp = jwtProvider.generateJwtTokenEmployee(authentication);
		UserDetails empDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwtemp, empDetails.getUsername(), empDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody SignUpForm signUpRequest) {
		if (customerrepojpa.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (customerrepojpa.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		CustomerModel customer = new CustomerModel(signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getUsername(),signUpRequest.getAddress(), signUpRequest.getEmail(),
				signUpRequest.getPhonenumber(),encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

				break;
			case "employee":
				Role employeeRole = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(employeeRole);

				break;
			default:
				Role customerRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(customerRole);
			}
		});

		customer.setRoles(roles);
		customerrepojpa.save(customer);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
}