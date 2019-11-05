package com.villera.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villera.model.AdminModel;


public class AdminPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer admin_id;
	
	private String first_name;
	
	private String last_name;
	
	private int age;
	
	private String username;
	
	 @JsonIgnore
	    private String password;
	
	private String email;
	

    private Collection<? extends GrantedAuthority> authorities;

    public AdminPrinciple(Integer admin_id, String first_name, 
			    		String last_name,int age,String username, String password, String email, 
			    		Collection<? extends GrantedAuthority> authorities) {
        this.admin_id = admin_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static AdminPrinciple build(AdminModel admin) {
        List<GrantedAuthority> authorities = admin.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new AdminPrinciple(
        		admin.getAdmin_id(),
        		admin.getFirst_name(),
        		admin.getLast_name(),
        		admin.getAge(),
        		admin.getUsername(),
        		admin.getPassword(),
        		admin.getEmail(),
                authorities
        );
    }

    public Integer getAdmin_id() {
        return admin_id;
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
        
        AdminPrinciple admin = (AdminPrinciple) o;
        return Objects.equals(admin_id, admin.admin_id);
    }
}
