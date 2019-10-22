package com.villera.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.villera.model.VehicleModel;



@Repository
public interface VehicleRepo extends CrudRepository<VehicleModel,Integer> {

}
