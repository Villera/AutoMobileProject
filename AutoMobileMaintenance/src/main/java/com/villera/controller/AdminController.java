package com.villera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.villera.model.AdminModel;
import com.villera.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminservice;
	
	@GetMapping("Admin/(admin_id)")
	public ResponseEntity<AdminModel> getAdminById(@PathVariable("admin_id") Integer admin_id){
		AdminModel admin=adminservice.getAdminById(admin_id);
		return new ResponseEntity<AdminModel>(admin,HttpStatus.OK);
	}
	
}
