package com.cms.backend.position.entity;

import java.util.UUID;

import com.cms.backend.common.entity.AuditableEntity;
import com.cms.backend.company.entity.Company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "positions",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "positions_company_id_name_key",
            columnNames = {"company_id", "name"}
        )
    }
)
public class Position extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "public_id", nullable = false, unique = true)
    private UUID publicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private String name;

    private String description;

    private Integer level;

    @Column(name = "is_active")
    private Boolean isActive = true;
}