package com.cms.backend.permission.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cms.backend.permission.dto.CreatePermissionRequest;
import com.cms.backend.permission.dto.PermissionResponse;
import com.cms.backend.permission.dto.UpdatePermissionRequest;
import com.cms.backend.permission.entity.Permission;
import com.cms.backend.permission.repository.PermissionRepository;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(
            PermissionRepository permissionRepository) {

        this.permissionRepository = permissionRepository;
    }

    public List<PermissionResponse> getAllPermissions() {

        return permissionRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PermissionResponse getPermissionById(
            UUID id) {

        Permission permission =
                permissionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Permission not found"));

        return mapToResponse(permission);
    }

    public PermissionResponse createPermission(
            CreatePermissionRequest request) {

        if (permissionRepository.existsByKey(
                request.key())) {

            throw new RuntimeException(
                    "Permission already exists");
        }

        Permission permission = new Permission();

        permission.setKey(request.key());
        permission.setDescription(
                request.description());

        permission.setPublicId(UUID.randomUUID());

        return mapToResponse(
                permissionRepository.save(permission));
    }

    public PermissionResponse updatePermission(
            UUID id,
            UpdatePermissionRequest request) {

        Permission permission =
                permissionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Permission not found"));

        permission.setKey(request.key());
        permission.setDescription(
                request.description());

        return mapToResponse(
                permissionRepository.save(permission));
    }

    private PermissionResponse mapToResponse(
            Permission permission) {

        return new PermissionResponse(
                permission.getPublicId(),
                permission.getKey(),
                permission.getDescription()
        );
    }
}