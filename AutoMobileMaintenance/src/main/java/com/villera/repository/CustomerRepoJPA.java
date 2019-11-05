package com.villera.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.villera.model.CustomerModel;


public interface CustomerRepoJPA extends JpaRepository<CustomerModel, Long> {
    Optional<CustomerModel> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}