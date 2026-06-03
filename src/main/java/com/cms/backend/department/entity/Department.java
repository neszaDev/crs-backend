package com.cms.backend.department.entity;

import com.cms.backend.common.entity.AuditableEntity;
import com.cms.backend.company.entity.Company;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({
    "hibernateLazyInitializer",
    "handler"
})
@Table(
    name = "departments",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "departments_company_id_name_key",
            columnNames = {"company_id", "name"}
        )
    }
)
public class Department extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String name;

    @Size(max = 1000)
    private String description;
}