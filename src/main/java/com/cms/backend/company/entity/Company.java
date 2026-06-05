package com.cms.backend.company.entity;

import java.util.UUID;

import com.cms.backend.common.entity.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({
        "hibernateLazyInitializer",
        "handler"
})
@Table(name = "companies")
public class Company extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "public_id", nullable = false, unique = true)
    private UUID publicId;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(name = "subscription_plan", nullable = false, length = 50)
    private String subscriptionPlan;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String status = "ACTIVE";
}