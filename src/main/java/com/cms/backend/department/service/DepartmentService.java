package com.cms.backend.department.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.backend.company.entity.Company;
import com.cms.backend.company.repository.CompanyRepository;
import com.cms.backend.department.dto.CreateDepartmentRequest;
import com.cms.backend.department.entity.Department;
import com.cms.backend.department.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;

    public DepartmentService(
            DepartmentRepository departmentRepository,
            CompanyRepository companyRepository) {

        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    public Department createDepartment(
            CreateDepartmentRequest request) {

        if (departmentRepository.existsByCompanyIdAndName(
                request.getCompanyId(),
                request.getName())) {

            throw new RuntimeException(
                    "Department already exists");
        }

        Company company = companyRepository
                .findById(request.getCompanyId())
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        Department department = new Department();

        department.setCompany(company);
        department.setName(request.getName());
        department.setDescription(
                request.getDescription());

        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}