package com.villera.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.villera.model.EmployeeModel;



public interface EmployeeRepoJPA extends JpaRepository<EmployeeModel, Long> {
    Optional<EmployeeModel> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
