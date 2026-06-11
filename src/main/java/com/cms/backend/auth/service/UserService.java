package com.cms.backend.auth.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cms.backend.auth.dto.CreateUserRequest;
import com.cms.backend.auth.dto.UserResponse;
import com.cms.backend.auth.entity.User;
import com.cms.backend.auth.repository.UserRepository;
import com.cms.backend.company.entity.Company;
import com.cms.backend.company.repository.CompanyRepository;
import com.cms.backend.role.entity.Role;
import com.cms.backend.role.repository.RoleRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final PasswordService passwordService;

    public UserService(
            UserRepository userRepository,
            CompanyRepository companyRepository,
            RoleRepository roleRepository,
            PasswordService passwordService) {

        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.roleRepository = roleRepository;
        this.passwordService = passwordService;
    }

    public UserResponse createUser(
            CreateUserRequest request) {

        if (userRepository.existsByEmailAndDeletedAtIsNull(
                request.email())) {

            throw new RuntimeException(
                    "Email already exists");
        }

        Company company =
                companyRepository.findById(
                        request.companyId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Company not found"));

        Role role =
                roleRepository.findById(
                        request.roleId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Role not found"));

        User user = new User();

        user.setPublicId(
                UUID.randomUUID());

        user.setCompany(
                company);

        user.setRole(
                role);

        user.setEmail(
                request.email());

        user.setPasswordHash(
                passwordService.hashPassword(
                        request.password()));

        user.setStatus(
                request.status() == null
                        ? "active"
                        : request.status());

        User savedUser =
                userRepository.save(user);

        return mapToResponse(
                savedUser);
    }

    public List<UserResponse> getAllUsers() {

        return userRepository
                .findByDeletedAtIsNull()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UserResponse mapToResponse(
            User user) {

        return new UserResponse(
                user.getPublicId(),
                user.getCompany().getId(),
                user.getRole().getId(),
                user.getEmail(),
                user.getStatus()
        );
    }
}