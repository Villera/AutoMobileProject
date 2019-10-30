package com.villera.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AutoCare_ServiceCentre")
public class ServiceCentreModel {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "ServiceCentre_ID")
	private Integer servicecentre_id;
	@Column(name = "Branch_Name")
	private String branch_name;
	@Column(name = "Contact_Number")
	private String contact_number;
	@Column(name = "Email")
	private String email;
	@Column(name = "Address")
	private String address;
	
	
	public Integer getServicecentre_id() {
		return servicecentre_id;
	}
	public void setServicecentre_id(Integer servicecentre_id) {
		this.servicecentre_id = servicecentre_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
