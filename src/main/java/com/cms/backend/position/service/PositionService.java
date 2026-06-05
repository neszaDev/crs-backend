package com.cms.backend.position.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cms.backend.company.repository.CompanyRepository;
import com.cms.backend.position.dto.PositionResponse;
import com.cms.backend.position.repository.PositionRepository;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final CompanyRepository companyRepository;

    public PositionService(
            PositionRepository positionRepository,
            CompanyRepository companyRepository) {

        this.positionRepository = positionRepository;
        this.companyRepository = companyRepository;
    }

public List<PositionResponse> getAllPositions() {
        return positionRepository.findAll()
                .stream()
                .map(position -> new PositionResponse(
                        position.getPublicId(),
                        position.getCompany() != null
                                ? position.getCompany().getName()
                                : null,
                        position.getName(),
                        position.getDescription(),
                        position.getLevel(),
                        position.getIsActive()
                    ))
                .toList();
    }
}