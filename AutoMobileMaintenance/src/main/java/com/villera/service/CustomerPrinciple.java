package com.villera.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villera.model.CustomerModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class CustomerPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer customerid;
	
	private String firstname;
	
	private String lastname;
	
	private String username;
	
	private String address;
	
	private String email;
	
	 @JsonIgnore
	    private String password;
	
	private String phonenumber;

   

    private Collection<? extends GrantedAuthority> authorities;

    public CustomerPrinciple(Integer customerid, String firstname, 
			    		String lastname,String username,String address, String email,String password, String phonenumber, 
			    		Collection<? extends GrantedAuthority> authorities) {
        this.customerid = customerid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.authorities = authorities;
    }

    public static CustomerPrinciple build(CustomerModel customer) {
        List<GrantedAuthority> authorities = customer.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new CustomerPrinciple(
                customer.getCustomerid(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getUsername(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getPhonenumber(),
                authorities
        );
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public String getUsername() {
        return username;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    public String getPhonenumber() {
        return phonenumber;
    }
   

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        CustomerPrinciple customer = (CustomerPrinciple) o;
        return Objects.equals(customerid, customer.customerid);
    }
}