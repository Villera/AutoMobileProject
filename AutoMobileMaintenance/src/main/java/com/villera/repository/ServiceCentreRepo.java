package com.villera.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.villera.model.ServiceCentreModel;

@Repository
public interface ServiceCentreRepo extends CrudRepository<ServiceCentreModel,Integer> {

}
