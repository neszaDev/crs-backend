package com.cms.backend.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.backend.company.repository.CompanyRepository;
import com.cms.backend.department.repository.DepartmentRepository;
import com.cms.backend.employee.entity.Employee;
import com.cms.backend.employee.repository.EmployeeRepository;
import com.cms.backend.position.repository.PositionRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            CompanyRepository companyRepository,
            DepartmentRepository departmentRepository,
            PositionRepository positionRepository) {

        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}