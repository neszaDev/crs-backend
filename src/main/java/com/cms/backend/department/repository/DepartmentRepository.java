package com.cms.backend.department.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.department.entity.Department;

public interface DepartmentRepository
        extends JpaRepository<Department, Integer> {

    boolean existsByCompanyIdAndName(
            Integer companyId,
            String name);

    Optional<Department> findByIdAndCompanyId(
            Integer id,
            Integer companyId);
}