package com.cms.backend.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.backend.position.dto.PositionResponse;
import com.cms.backend.role.dto.CreateRoleRequest;
import com.cms.backend.role.dto.RoleResponse;
import com.cms.backend.role.entity.Role;
import com.cms.backend.role.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> new RoleResponse(
                        role.getPublicId(),
                        role.getName()
                    ))
                .toList();
    }

    public Role createRole(CreateRoleRequest request) {

        if (roleRepository.existsByName(request.name())) {
            throw new RuntimeException("Role already exists");
        }

        Role role = new Role();
        role.setName(request.name());

        return roleRepository.save(role);
    }
}