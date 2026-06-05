package com.cms.backend.company.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cms.backend.company.dto.CompanyResponse;
import com.cms.backend.company.dto.CreateCompanyRequest;
import com.cms.backend.company.dto.UpdateCompanyRequest;
import com.cms.backend.company.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(
            CompanyService companyService) {

        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyResponse findByPublicId(
            @PathVariable UUID id) {

        return companyService.getCompanyByPublicId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(
            @Valid
            @RequestBody
            CreateCompanyRequest request) {

        return companyService.createCompany(request);
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompany(
            @PathVariable UUID id,
            @Valid
            @RequestBody
            UpdateCompanyRequest request) {

        return companyService.updateCompany(
                id,
                request);
    }
}