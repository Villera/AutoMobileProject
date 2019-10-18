package com.villera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.villera.model.AdminModel;
import com.villera.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminservice;
	
	@GetMapping("admin/{admin_id}")
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
}
	

