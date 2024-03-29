package com.villera.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.villera.message.request.SignUpForm;
import com.villera.message.response.ResponseMessage;
import com.villera.model.AdminModel;
import com.villera.model.CustomerModel;
import com.villera.model.EmployeeModel;
import com.villera.model.Role;
import com.villera.model.RoleName;
import com.villera.model.ServiceCentreModel;
import com.villera.model.VehicleModel;
import com.villera.repository.RoleRepository;
import com.villera.service.AdminService;

@RestController
@CrossOrigin(origins= "*", maxAge = 3600)                                  //"http://localhost:4200"
//@RequestMapping(value="/api/administrator")
public class AdminController {

	@Autowired
	private AdminService adminservice;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	
	@GetMapping("/admin/{admin_id}")
	public ResponseEntity<AdminModel> getAdminById(@PathVariable("admin_id") Integer admin_id){
		AdminModel admin=adminservice.getAdminById(admin_id);
		return new ResponseEntity<AdminModel>(admin,HttpStatus.OK);
		}
	
	
	@PostMapping("/adimini")
	public ResponseEntity<Void> addAdmin(@RequestBody AdminModel admin, UriComponentsBuilder builder){
		

		//Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

		AdminModel newAdmin = admin;
		String password = newAdmin.getPassword();
		newAdmin.setPassword(encoder.encode(password));
		newAdmin.setRoles(roles);
		
		AdminModel flag=adminservice.addAdmin(newAdmin);
		if(flag==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders header=new HttpHeaders();
		header.setLocation(builder.path("/admin/{admin_id}").buildAndExpand(admin.getAdmin_id()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-admin/{admin_id}")
    public ResponseEntity<String> updateAdmin(@RequestBody AdminModel admin,
      @PathVariable("admin_id") Integer admin_id) {
        adminservice.updateAdmin(admin);
        return ResponseEntity.ok("Administrator Updated");
  }
	
	@GetMapping("/alladmins")
	public ResponseEntity<List<AdminModel>> getAllAdmins(){
		List<AdminModel> adminlist=adminservice.getAllAdmins();
		return new ResponseEntity<List<AdminModel>>(adminlist,HttpStatus.OK);
	}
	
	
	//For Customers

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/customer/{customerid}")
	public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("customerid") Integer customerid){
		CustomerModel cus=adminservice.getCustomerById(customerid);
		return new ResponseEntity<CustomerModel>(cus,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/api/administrator/allcustomers")
	public ResponseEntity<List<CustomerModel>> getAllCustomers(){
		List<CustomerModel> cuslist=adminservice.getAllCustomers();
		return new ResponseEntity<List<CustomerModel>>(cuslist,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/administrator/customer")
	public ResponseEntity<Void> addCustomer(@RequestBody CustomerModel cus, UriComponentsBuilder builder){
		CustomerModel flag=adminservice.addCustomer(cus);
		if(flag==null)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders header=new HttpHeaders();
		header.setLocation(builder.path("/customer/{customerid}").buildAndExpand(cus.getCustomerid()).toUri());
		return new ResponseEntity<Void>(header,HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
		@DeleteMapping("/api/removecustomer/{customerid}")
		public ResponseEntity<Void> deleteCustomer(@PathVariable("customerid") Integer customerid){adminservice.deleteCustomer(customerid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	@PreAuthorize("hasRole('ADMIN')")
		  @PutMapping("/update-customer/{customerid}")
		    public ResponseEntity<String> updateCustomer(@RequestBody CustomerModel customer,
		      @PathVariable("customerid") Integer customerid) {
		        adminservice.updateCustomer(customer);
		        return ResponseEntity.ok("Customer Updated");
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
	
	 @PutMapping("/update-vehicle/{vehicle_id}")
	    public ResponseEntity<String> updateVehicle(@RequestBody VehicleModel vhcl,
	      @PathVariable("vehicle_id") Integer vehicle_id) {
	        adminservice.updateVehicle(vhcl);
	        return ResponseEntity.ok("Vehicle Updated");
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
	
	 @PutMapping("/update-servicecentre/{servicecentre_id}")
	    public ResponseEntity<String> updateServiceCentre(@RequestBody ServiceCentreModel service,
	      @PathVariable("servicecentre_id") Integer servicecentre_id) {
		 System.out.println("CALLING UPDATE");
	        adminservice.updateServiceCentre(service);
	        return ResponseEntity.ok("ServiceCentre Updated");
	  }
	 
	 //For Employee
	 
	 @GetMapping("/employee/{employee_id}")
		public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable("employee_id") Integer employee_id){
			EmployeeModel employee=adminservice.getEmployeeById(employee_id);
			return new ResponseEntity<EmployeeModel>(employee,HttpStatus.OK);
		}
		
		@GetMapping("/allemployees")
		public ResponseEntity<List<EmployeeModel>> getAllEmployees(){
			List<EmployeeModel> emplist=adminservice.getAllEmployees();
			return new ResponseEntity<List<EmployeeModel>>(emplist,HttpStatus.OK);
		}
		
		@PostMapping("/add_employee")
		public ResponseEntity<Void> addEmployee(@RequestBody EmployeeModel emp, UriComponentsBuilder builder){
			EmployeeModel flag=adminservice.addEmployee(emp);
			if(flag==null)
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			HttpHeaders header=new HttpHeaders();
			header.setLocation(builder.path("/employee/{employee_id}").buildAndExpand(emp. getEmployee_id()).toUri());
			return new ResponseEntity<Void>(header,HttpStatus.CREATED);
		}
		
		@DeleteMapping("/delete_employee/{employee_id}")
		public ResponseEntity<Void> deleteEmployee(@PathVariable("employee_id") Integer employee_id){adminservice.deleteEmployee(employee_id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
		 @PutMapping("/update-employee/{employee_id}")
		    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeModel emp,
		      @PathVariable("servicecentre_id") Integer servicecentre_id) {
		        adminservice.updateEmployee(emp);
		        return ResponseEntity.ok("Employee Updated");
		  }
		 
		 
		 

	 
}
	

