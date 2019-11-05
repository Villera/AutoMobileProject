package com.villera.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "AutoCus", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
            }),
            @UniqueConstraint(columnNames = {
                "email"
            })
    })
public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JWT_VILLERA_AUTOCUS_SEQ")
    @SequenceGenerator(sequenceName = "jwt_villera_autocus_seq", initialValue = 1, allocationSize = 1, name = "JWT_VILLERA_AUTOCUS_SEQ")
	@Column(name = "customer_id")
	private Integer customerid;
	
	@Column(name = "First_Name")
	private String firstname;
	@Column(name = "Last_Name")
	private String lastname;
	@Column(name = "username")
	private String username;
	@Column(name = "Address")
	private String address;
	@Column(name = "email")
	private String email;
	@Column(name="Password")
	private String password;
	@Column(name = "Phone_Number")
	private String phonenumber;
	
	@OneToOne(fetch = FetchType.LAZY,
	        cascade = CascadeType.ALL,
	        mappedBy = "customer1")	
	private VehicleModel vehiclemodel;
	
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "AutoCus_AutoRoles", 
	    	joinColumns = {@JoinColumn(name = "customer_id")}, 
	    	inverseJoinColumns = {@JoinColumn(name = "role_id")})
	    private Set<Role> roles = new HashSet<>();

	
	public CustomerModel() {}
	
	public CustomerModel(String firstname, String lastname,String username, String address, String email,
			String phonenumber,String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.address = address;
		this.email = email;
		this.phonenumber = phonenumber;
		this.password =password;
	}




	public VehicleModel getVehiclemodel() {
		return vehiclemodel;
	}
	public void setVehiclemodel(VehicleModel vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}
	public Integer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
