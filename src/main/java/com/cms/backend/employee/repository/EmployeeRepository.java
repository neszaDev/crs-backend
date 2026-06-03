package com.cms.backend.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.employee.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {

    boolean existsByCompanyIdAndEmployeeCode(
            Integer companyId,
            String employeeCode);

    Optional<Employee> findByIdAndCompanyId(
            Integer id,
            Integer companyId);
}