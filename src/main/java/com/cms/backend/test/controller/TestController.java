package com.cms.backend.test.controller;

import com.cms.backend.common.exception.ResourceNotFoundException;
import com.cms.backend.common.response.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Service
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

        @GetMapping("/success")
        public ResponseEntity<ApiResponse<String>> success() {

                log.info("Success endpoint called");

                return ResponseEntity.ok(
                                ApiResponse.success(
                                                "Request successful",
                                                "Hello World"));
        }

        @GetMapping("/error")
        public ResponseEntity<String> error() {

                log.error("Error endpoint called");

                throw new ResourceNotFoundException(
                                "Employee not found");
        }

        @PostMapping("/validation")
        public ResponseEntity<ApiResponse<TestRequest>> validation(
                        @Valid @RequestBody TestRequest request) {

                log.info(
                                "Validation endpoint called with username={}",
                                request.username());

                return ResponseEntity.ok(
                                ApiResponse.success(
                                                "Validation successful",
                                                request));
        }

        public record TestRequest(

                        @NotBlank(message = "USERNAME_REQUIRED") String username

        ) {
        }
}