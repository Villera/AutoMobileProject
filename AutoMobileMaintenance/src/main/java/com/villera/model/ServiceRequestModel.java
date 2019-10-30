package com.villera.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AutoCare_ServiceRequests")
public class ServiceRequestModel {
	
	@Id@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "ServiceRequest_ID")
	private Integer servicerequest_id;
	@Column(name="ServiceCentre_ID")
	private Integer servicecentre_id;
	@Column(name="Vehicle_Registration_Number")
	private long registration_number;
	@Column(name = "Service_Request_1")
	private String service_request_1;
	@Column(name = "Service_Request_2")
	private String service_request_2;
	@Column(name = "Service_Request_3")
	private String service_request_3;
	@Column(name = "Service_Request_4")
	private String service_request_4;
	@Column(name = "Service_Request_5")
	private String service_request_5;
	@Column(name = "Service_Date")
	private Date service_date;
	public Integer getServicerequest_id() {
		return servicerequest_id;
	}
	public void setServicerequest_id(Integer servicerequest_id) {
		this.servicerequest_id = servicerequest_id;
	}
	public Integer getServicecentre_id() {
		return servicecentre_id;
	}
	public void setServicecentre_id(Integer servicecentre_id) {
		this.servicecentre_id = servicecentre_id;
	}
	public long getRegistration_number() {
		return registration_number;
	}
	public void setRegistration_number(long registration_number) {
		this.registration_number = registration_number;
	}
	public String getService_request_1() {
		return service_request_1;
	}
	public void setService_request_1(String service_request_1) {
		this.service_request_1 = service_request_1;
	}
	public String getService_request_2() {
		return service_request_2;
	}
	public void setService_request_2(String service_request_2) {
		this.service_request_2 = service_request_2;
	}
	public String getService_request_3() {
		return service_request_3;
	}
	public void setService_request_3(String service_request_3) {
		this.service_request_3 = service_request_3;
	}
	public String getService_request_4() {
		return service_request_4;
	}
	public void setService_request_4(String service_request_4) {
		this.service_request_4 = service_request_4;
	}
	public String getService_request_5() {
		return service_request_5;
	}
	public void setService_request_5(String service_request_5) {
		this.service_request_5 = service_request_5;
	}
	public Date getService_date() {
		return service_date;
	}
	public void setService_date(Date service_date) {
		this.service_date = service_date;
	}
	
	

}
