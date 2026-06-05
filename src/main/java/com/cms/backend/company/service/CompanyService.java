package com.cms.backend.company.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cms.backend.company.dto.CompanyResponse;
import com.cms.backend.company.dto.CreateCompanyRequest;
import com.cms.backend.company.dto.UpdateCompanyRequest;
import com.cms.backend.company.entity.Company;
import com.cms.backend.company.repository.CompanyRepository;

@Service
public class CompanyService {

        private final CompanyRepository companyRepository;

        public CompanyService(
                        CompanyRepository companyRepository) {

                this.companyRepository = companyRepository;
        }

        public CompanyResponse createCompany(
                        CreateCompanyRequest request) {

                if (companyRepository.existsByName(
                                request.getName())) {

                        throw new RuntimeException(
                                        "Company already exists");
                }

                Company company = new Company();

                company.setName(request.getName());
                company.setPublicId(UUID.randomUUID());
                company.setSubscriptionPlan(
                                request.getSubscriptionPlan());

                Company savedCompany = companyRepository.save(company);

                return mapToResponse(savedCompany);
        }

        public List<CompanyResponse> getAllCompanies() {

                return companyRepository.findAll()
                                .stream()
                                .map(this::mapToResponse)
                                .toList();
        }

        public CompanyResponse getCompanyByPublicId(
                        UUID publicId) {

                Company company = companyRepository.findByPublicId(publicId)
                                .orElseThrow(() -> new RuntimeException(
                                                "Company not found"));

                return mapToResponse(company);
        }

        public CompanyResponse updateCompany(
                        UUID id,
                        UpdateCompanyRequest request) {

                Company company = companyRepository.findByPublicId(id)
                                .orElseThrow(() -> new RuntimeException(
                                                "Company not found"));

                company.setName(request.getName());
                company.setSubscriptionPlan(
                                request.getSubscriptionPlan());
                company.setStatus(request.getStatus());

                Company updated = companyRepository.save(company);

                return mapToResponse(updated);
        }

        private CompanyResponse mapToResponse(
                        Company company) {

                return new CompanyResponse(
                                company.getPublicId(),
                                company.getName(),
                                company.getSubscriptionPlan(),
                                company.getStatus());
        }
}