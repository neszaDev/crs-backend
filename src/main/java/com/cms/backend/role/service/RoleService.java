package com.cms.backend.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.backend.role.dto.CreateRoleRequest;
import com.cms.backend.role.entity.Role;
import com.cms.backend.role.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role createRole(CreateRoleRequest request) {

        if (roleRepository.existsByName(request.getName())) {
            throw new RuntimeException("Role already exists");
        }

        Role role = new Role();
        role.setName(request.getName());

        return roleRepository.save(role);
    }
}