package com.villera.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.villera.model.AdminModel;

@Repository
public interface AdminRepo extends CrudRepository<AdminModel,Integer> {

}
