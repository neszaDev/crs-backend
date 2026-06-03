package com.cms.backend.employee.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateEmployeeRequest {

    private Integer companyId;
    private Integer departmentId;
    private Integer positionId;

    private String employeeCode;

    private String firstName;
    private String lastName;

    private String nickname;

    private String gender;

    private LocalDate birthDate;

    private String personalEmail;

    private String phone;

    private String address;

    private String employmentType;

    private String employmentStatus;

    private LocalDate hireDate;

    private BigDecimal baseSalary;
}