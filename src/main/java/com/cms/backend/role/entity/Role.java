package com.cms.backend.role.entity;

import java.util.UUID;

import com.cms.backend.common.entity.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "roles",
    uniqueConstraints = {
        @UniqueConstraint(name = "roles_name_key", columnNames = "name")
    }
)
public class Role extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "public_id", nullable = false, unique = true)
    private UUID publicId;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, unique = true)
    private String name;
}