package com.villera.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Customer_Table")
public class CustomerModel {

	@Id //@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@GeneratedValue(generator="incrementGenerator2", strategy=GenerationType.SEQUENCE)
	@GenericGenerator(name="incrementGenerator2", strategy="increment")
	@Column(name = "Customer_Id")
	private Integer customerid;
	@Column(name = "First_Name")
	private String firstname;
	@Column(name = "Last_Name")
	private String lastname;
	@Column(name = "Address")
	private String address;
	@Column(name = "Email")
	private String email;
	@Column(name = "Phone_Number")
	private String phonenumber;
	
	@OneToOne(fetch = FetchType.LAZY,
	        cascade = CascadeType.ALL,
	        mappedBy = "customer1")	
	private VehicleModel vehiclemodel;

	
	public CustomerModel() {}
	public CustomerModel(Integer customerid, String firstname, String lastname, String address, String email,
			String phonenumber) {
		super();
		this.customerid = customerid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.email = email;
		this.phonenumber = phonenumber;
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
	
	
}
