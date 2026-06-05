package com.cms.backend.company.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.company.entity.Company;

public interface CompanyRepository
        extends JpaRepository<Company, Integer> {

    boolean existsByName(String name);

    Optional<Company> findByPublicId(UUID publicId);
}