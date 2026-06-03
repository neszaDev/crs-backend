package com.cms.backend.employee.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cms.backend.common.entity.AuditableEntity;
import com.cms.backend.company.entity.Company;
import com.cms.backend.department.entity.Department;
import com.cms.backend.position.entity.Position;
import com.cms.backend.auth.entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "employees",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "employees_company_id_employee_code_key",
            columnNames = {"company_id", "employee_code"}
        )
    }
)
public class Employee extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_employee_id")
    private Employee manager;

    @Column(name = "employee_code", nullable = false)
    private String employeeCode;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String nickname;
    private String gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "personal_email")
    private String personalEmail;

    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "employment_status")
    private String employmentStatus;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "base_salary")
    private BigDecimal baseSalary;
}