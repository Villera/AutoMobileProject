package com.villera.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.villera.model.AdminModel;
import com.villera.repository.AdminRepo;

@Service
public class AdminService {

	@Autowired
	private AdminRepo adminrepo;
	
	public AdminModel getAdminById(Integer admin_id) {
		AdminModel admin = adminrepo.findById(admin_id).get();
		return admin;
	}
	
	public void updateAdmin(AdminModel admin) {
		adminrepo.save(admin);
	}
		
}
