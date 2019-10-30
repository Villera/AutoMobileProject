package com.villera.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.villera.model.EmployeeModel;

@Repository
public interface EmployeeRepo extends CrudRepository<EmployeeModel,Integer> {

}

