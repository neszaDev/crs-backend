package com.cms.backend.auth.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cms.backend.auth.dto.CreateUserRequest;
import com.cms.backend.auth.dto.UserResponse;
import com.cms.backend.auth.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService) {

        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(
            @Valid
            @RequestBody
            CreateUserRequest request) {

        return userService.createUser(
                request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {

        return userService.getAllUsers();
    }
}