package com.cms.backend.test.controller;

import com.cms.backend.common.response.ApiResponse;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@RestController
@Service
@RequiredArgsConstructor
@RequestMapping("/test/redis")
public class RedisTestController {
        @Autowired
        private final RedisTemplate<String, Object> redisTemplate;

        @GetMapping
        public ResponseEntity<ApiResponse<Object>> redis() {

                log.info("Redis endpoint called");

                redisTemplate.opsForValue().set(
                                "test:key",
                                "Hello Redis");

                Object value = redisTemplate.opsForValue().get(
                                "test:key");

                return ResponseEntity.ok(
                                ApiResponse.success(
                                                "Redis successful",
                                                value));
        }
}