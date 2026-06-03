package com.cms.backend.position.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.position.entity.Position;

public interface PositionRepository
        extends JpaRepository<Position, Integer> {

    boolean existsByCompanyIdAndName(
            Integer companyId,
            String name);
}