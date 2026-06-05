package com.cms.backend.permission.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.permission.entity.Permission;

public interface PermissionRepository
        extends JpaRepository<Permission, UUID> {

    boolean existsByKey(String key);

    Optional<Permission> findByPublicId(UUID publicId);
}