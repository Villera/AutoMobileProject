package com.villera.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.villera.model.AdminModel;



public interface AdminRepoJPA extends JpaRepository<AdminModel, Long> {
    Optional<AdminModel> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}