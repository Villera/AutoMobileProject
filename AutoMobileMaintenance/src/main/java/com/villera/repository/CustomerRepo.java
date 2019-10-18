package com.villera.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.villera.model.CustomerModel;

@Repository
public interface CustomerRepo extends CrudRepository<CustomerModel,Integer> {

}
