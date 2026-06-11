package com.cms.backend.auth.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.backend.auth.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Login / Authentication
    Optional<User> findByEmailAndDeletedAtIsNull(String email);

    // Validation
    boolean existsByEmailAndDeletedAtIsNull(String email);

    // Company Users
    List<User> findByCompanyIdAndDeletedAtIsNull(Integer companyId);

    Optional<User> findByPublicIdAndDeletedAtIsNull(
            UUID publicId);

    List<User> findByDeletedAtIsNull();
}