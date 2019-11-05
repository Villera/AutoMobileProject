package com.villera.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "AutoAdmin", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
            }),
            @UniqueConstraint(columnNames = {
                "email"
            })
    })
public class AdminModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JWT_VILLERA_AUTOADMIN_SEQ")
    @SequenceGenerator(sequenceName = "jwt_villera_autoadmin_seq", initialValue = 1, allocationSize = 1, name = "JWT_VILLERA_AUTOADMIN_SEQ")
	private Integer admin_id;
	
	@Column(name="FirstName")
	private String first_name;
	@Column(name="LastName")
	private String last_name;
	@Column(name="Age")
	private int age;
	@Column(name="UserName")
	private String username;
	@Column(name="Password")
	private String password;
	@Column(name="Email")
	private String email;
	
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "AutoAdmin_AutoRoles", 
	    	joinColumns = {@JoinColumn(name = "admin_id")}, 
	    	inverseJoinColumns = {@JoinColumn(name = "role_id")})
	    private Set<Role> roles = new HashSet<>();
	
	
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}
