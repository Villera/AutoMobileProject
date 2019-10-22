package com.villera.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.villera.model.AdminModel;
import com.villera.model.CustomerModel;
import com.villera.model.ServiceCentreModel;
import com.villera.model.VehicleModel;
import com.villera.service.AdminService;

@RestController
@RequestMapping(value="/administrator")
public class AdminController {

	@Autowired
	private AdminService adminservice;
	
	
	@GetMapping("/admin/{admin_id}")
	public ResponseEntity<AdminModel> getAdminById(@PathVariable("admin_id") Integer admin_id){
		AdminModel admin=adminservice.getAdminById(admin_id);
		return new ResponseEntity<AdminModel>(admin,HttpStatus.OK);
		}
	
	@PostMapping("/adimini")
	public ResponseEntity<Void> addAdmin(@RequestBody AdminModel admin, UriComponentsBuilder builder){
		AdminModel flag=adminservice.addAdmin(admin);
		if(flag==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders header=new HttpHeaders();
		header.setLocation(builder.path("/admin/{admin_id}").buildAndExpand(admin.getAdmin_id()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
	}
	
	
	//For Customers
	@GetMapping("/customer/{customerid}")
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("customerid") Integer customerid){
		CustomerModel cus=adminservice.getCustomerById(customerid);
		return new ResponseEntity<CustomerModel>(cus,HttpStatus.OK);
	}
	
	@GetMapping("/allcustomers")
	public ResponseEntity<List<CustomerModel>> getAllCustomers(){
		List<CustomerModel> cuslist=adminservice.getAllCustomers();
		return new ResponseEntity<List<CustomerModel>>(cuslist,HttpStatus.OK);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<Void> addCustomer(@RequestBody CustomerModel cus, UriComponentsBuilder builder){
		CustomerModel flag=adminservice.addCustomer(cus);
		if(flag==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders header=new HttpHeaders();
		header.setLocation(builder.path("/customer/{customerid}").buildAndExpand(cus.getCustomerid()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
	}
		@DeleteMapping("/remove_customer/{customerid}")
		public ResponseEntity<Void> deleteCustomer(@PathVariable("customerid") Integer customerid){adminservice.deleteCustomer(customerid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
		
	//For Vehicles
	
	@GetMapping("/vehicle/{vehicle_id}")
	public ResponseEntity<VehicleModel> getVehicleById(@PathVariable("vehicle_id") Integer vehicle_id){
		VehicleModel vhcl=adminservice.getVehicleById(vehicle_id);
		return new ResponseEntity<VehicleModel>(vhcl,HttpStatus.OK);
	}
	
	@GetMapping("/allvehicles")
	public ResponseEntity<List<VehicleModel>> getAllVehicles(){
		List<VehicleModel> vhcllist=adminservice.getAllVehicles();
		return new ResponseEntity<List<VehicleModel>>(vhcllist,HttpStatus.OK);
	}
	
	@PostMapping("/add_vehicle")
	public ResponseEntity<Void> addVehicle(@RequestBody VehicleModel vhcl, UriComponentsBuilder builder){
		VehicleModel flag=adminservice.addVehicle(vhcl);
		if(flag==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders header=new HttpHeaders();
		header.setLocation(builder.path("/vehicle/{vehicle_id}").buildAndExpand(vhcl.getVehicle_id()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/remove_vehicle/{vehicle_id}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicle_id") Integer vehicle_id){adminservice.deleteVehicle(vehicle_id);
	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//For ServiceCentres
	
	@GetMapping("/servicecentre/{servicecentre_id}")
	public ResponseEntity<ServiceCentreModel> getServiceCentreById(@PathVariable("servicecentre_id") Integer servicecentre_id){
		ServiceCentreModel service=adminservice.getServiceCentreById(servicecentre_id);
		return new ResponseEntity<ServiceCentreModel>(service,HttpStatus.OK);
	}
	
	@GetMapping("/allservicecentres")
	public ResponseEntity<List<ServiceCentreModel>> getAllServiceCentres(){
		List<ServiceCentreModel> servicelist=adminservice.getAllServiceCentres();
		return new ResponseEntity<List<ServiceCentreModel>>(servicelist,HttpStatus.OK);
	}
	
	@PostMapping("/add_servicecentre")
	public ResponseEntity<Void> addServiceCentre(@RequestBody ServiceCentreModel service, UriComponentsBuilder builder){
		ServiceCentreModel flag=adminservice.addServiceCentre(service);
		if(flag==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders header=new HttpHeaders();
		header.setLocation(builder.path("/servicecentre/{servicecentre_id}").buildAndExpand(service.getServicecentre_id()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete_servicecentre/{servicecentre_id}")
	public ResponseEntity<Void> deleteServiceCentre(@PathVariable("servicecentre_id") Integer servicecentre_id){adminservice.deleteServiceCentre(servicecentre_id);
	return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
	

