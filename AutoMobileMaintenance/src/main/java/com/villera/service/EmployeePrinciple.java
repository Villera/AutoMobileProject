package com.villera.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villera.model.EmployeeModel;

public class EmployeePrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer employee_id;
	
	private String first_name;
	
	private String last_name;
	
	private int age;
	
	private String username;
	
	 @JsonIgnore
	    private String password;
	
	private String email;
	

    private Collection<? extends GrantedAuthority> authorities;

    public EmployeePrinciple(Integer employee_id, String first_name, 
			    		String last_name,int age,String username, String password, String email, 
			    		Collection<? extends GrantedAuthority> authorities) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static EmployeePrinciple build(EmployeeModel emp) {
        List<GrantedAuthority> authorities = emp.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new EmployeePrinciple(
        		emp.getEmployee_id(),
        		emp.getFirst_name(),
        		emp.getLast_name(),
        		emp.getAge(),
        		emp.getUsername(),
        		emp.getPassword(),
        		emp.getEmail(),
                authorities
        );
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getAge() {
        return age;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getEmail() {
        return email;
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
        
        EmployeePrinciple emp = (EmployeePrinciple) o;
        return Objects.equals(employee_id, emp.employee_id);
    }
}

