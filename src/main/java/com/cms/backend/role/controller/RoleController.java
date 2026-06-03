package com.cms.backend.role.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cms.backend.role.dto.CreateRoleRequest;
import com.cms.backend.role.entity.Role;
import com.cms.backend.role.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role createRole(
            @Valid @RequestBody CreateRoleRequest request) {
        return roleService.createRole(request);
    }
}