package com.villera.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.villera.model.AdminModel;
import com.villera.model.CustomerModel;
import com.villera.model.ServiceCentreModel;
import com.villera.model.VehicleModel;
import com.villera.repository.AdminRepo;
import com.villera.repository.CustomerRepo;
import com.villera.repository.ServiceCentreRepo;
import com.villera.repository.VehicleRepo;

@Service
public class AdminService {

	@Autowired
	private AdminRepo adminrepo;
	
	@Autowired
	private VehicleRepo vehiclerepo;
	
	@Autowired
	private ServiceCentreRepo servicecentrerepo;
	
	@Autowired
	private CustomerRepo customerrepo;
	
	
	public AdminModel getAdminById(Integer admin_id) {
		AdminModel admin = adminrepo.findById(admin_id).get();
		return admin;
	}
	
	public void updateAdmin(AdminModel admin) {
		adminrepo.save(admin);
	}
	
	public AdminModel addAdmin(AdminModel admin){
		AdminModel addadmin =adminrepo.save(admin);
		return addadmin;
	}
	
	
	//Crud Operation For Customer
	public CustomerModel getCustomerById(Integer cusId) {
		CustomerModel cus = customerrepo.findById(cusId).get();
		return cus;
	}
	
	public List<CustomerModel> getAllCustomers(){
		List<CustomerModel> cuslist=new ArrayList<>();
		customerrepo.findAll().forEach(e->cuslist.add(e));
		return cuslist;
	}
	public CustomerModel addCustomer(CustomerModel cus){
		CustomerModel cuslist =customerrepo.save(cus);
		return cuslist;
	}
	public void updateCustomer(CustomerModel cus) {
		customerrepo.save(cus);
	}
	
	public void deleteCustomer(Integer cusId) {
		customerrepo.deleteById(cusId);
	}
	
	//Crud Operation For Vehicle
	public VehicleModel getVehicleById(Integer vehicle_id) {
		VehicleModel vhcl = vehiclerepo.findById(vehicle_id).get();
		return vhcl;
	}
	
	public List<VehicleModel> getAllVehicles(){
		List<VehicleModel> vehiclelist=new ArrayList<>();
		vehiclerepo.findAll().forEach(e->vehiclelist.add(e));
		return vehiclelist;
	}
	public VehicleModel addVehicle(VehicleModel vhcl){
		VehicleModel vehiclelist =vehiclerepo.save(vhcl);
		return vehiclelist;
	}
	public void updateVehicle(VehicleModel vhcl) {
		vehiclerepo.save(vhcl);
	}
	
	public void deleteVehicle(Integer vhclId) {
		vehiclerepo.deleteById(vhclId);
	}
	
	//Crud Operation For Service centre
	
	public ServiceCentreModel getServiceCentreById(Integer servicecentre_id) {
		ServiceCentreModel service = servicecentrerepo.findById(servicecentre_id).get();
		return service;
	}
	
	public List<ServiceCentreModel> getAllServiceCentres(){
		List<ServiceCentreModel> servicelist=new ArrayList<>();
		servicecentrerepo.findAll().forEach(e->servicelist.add(e));
		return servicelist;
	}
	public ServiceCentreModel addServiceCentre(ServiceCentreModel service){
		ServiceCentreModel servicelist =servicecentrerepo.save(service);
		return servicelist;
	}
	public void updateServiceCentre(ServiceCentreModel service) {
		servicecentrerepo.save(service);
	}
	
	public void deleteServiceCentre(Integer serviceId) {
		servicecentrerepo.deleteById(serviceId);
	}
		
		
}
