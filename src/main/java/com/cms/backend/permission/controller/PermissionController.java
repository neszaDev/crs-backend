package com.cms.backend.permission.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cms.backend.permission.dto.CreatePermissionRequest;
import com.cms.backend.permission.dto.PermissionResponse;
import com.cms.backend.permission.dto.UpdatePermissionRequest;
import com.cms.backend.permission.service.PermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(
            PermissionService permissionService) {

        this.permissionService = permissionService;
    }

    @GetMapping
    public List<PermissionResponse> getAllPermissions() {

        return permissionService.getAllPermissions();
    }

    @GetMapping("/{id}")
    public PermissionResponse getPermissionById(
            @PathVariable UUID id) {

        return permissionService.getPermissionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionResponse createPermission(
            @Valid
            @RequestBody
            CreatePermissionRequest request) {

        return permissionService.createPermission(
                request);
    }

    @PutMapping("/{id}")
    public PermissionResponse updatePermission(
            @PathVariable UUID id,
            @Valid
            @RequestBody
            UpdatePermissionRequest request) {

        return permissionService.updatePermission(
                id,
                request);
    }
}