package com.villera.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "Vehicles")
public class VehicleModel {

	@Id //@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@GeneratedValue(generator="incrementGenerator", strategy=GenerationType.SEQUENCE)
	@GenericGenerator(name="incrementGenerator", strategy="increment")
    @Column(name = "Vehicle_ID")
	private Integer vehicle_id;
	@Column(name = "Registration_number")
	private long registration_number;
	@Column(name = "Model")
	private String model;
	@Column(name = "Brand")
	private String brand;
	@Column(name = "Warrantee_Start_Date")
	private Date warrantee_start_date;
	@Column(name = "Warrantee_End_Date")
	private Date warrantee_end_date;
	
	
	
	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="Vehicle_Customer", joinColumns = @JoinColumn(name="vehicle_id"),
	inverseJoinColumns = @JoinColumn(name="customerid"))*/
	
	
	@OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "Customer_Id", nullable = false)
private CustomerModel customer1;
	
	public VehicleModel() {}
	public VehicleModel(Integer vehicle_id, long registration_number, String model, String brand,
			Date warrantee_start_date, Date warrantee_end_date) {
		super();
		this.vehicle_id = vehicle_id;
		this.registration_number = registration_number;
		this.model = model;
		this.brand = brand;
		this.warrantee_start_date = warrantee_start_date;
		this.warrantee_end_date = warrantee_end_date;
	}


	
	public CustomerModel getCustomer1() {
		return customer1;
	}
	public void setCustomer1(CustomerModel customer1) {
		this.customer1 = customer1;
	}
	public Integer getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(Integer vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public long getRegistration_number() {
		return registration_number;
	}
	public void setRegistration_number(long registration_number) {
		this.registration_number = registration_number;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Date getWarrantee_start_date() {
		return warrantee_start_date;
	}
	public void setWarrantee_start_date(Date warrantee_start_date) {
		this.warrantee_start_date = warrantee_start_date;
	}
	public Date getWarrantee_end_date() {
		return warrantee_end_date;
	}
	public void setWarrantee_end_date(Date warrantee_end_date) {
		this.warrantee_end_date = warrantee_end_date;
	}
	
}
