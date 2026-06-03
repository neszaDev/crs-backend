package com.cms.backend.position.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.backend.position.entity.Position;
import com.cms.backend.position.service.PositionService;

@RestController
@RequestMapping("/api/v1/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(
            PositionService positionService) {

        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }
}