package com.example.shop.controller;

import com.example.shop.domain.account.Company;
import com.example.shop.domain.account.CompanyRepository;
import com.example.shop.dto.accont.RequestRegisterCompanyDto;
import com.example.shop.dto.common.ResponseSavedIdDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final CompanyRepository companyRepository;

    @PostMapping("/company")
    public ResponseEntity<?> registerCompany(@RequestBody RequestRegisterCompanyDto requestDto) {

        Company savedCompany = companyRepository.save(
                Company.builder()
                        .companyId(requestDto.getCompanyId())
                        .companyPw(requestDto.getCompanyPw())
                        .companyName(requestDto.getCompanyName())
                        .companyCeo(requestDto.getCompanyCeo())
                        .companyNumber(requestDto.getCompanyNumber())
                        .companyAddress1(requestDto.getCompanyAddress1())
                        .companyAddress2(requestDto.getCompanyAddress2())
                        .companyEmail(requestDto.getCompanyEmail())
                        .build());

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(savedCompany.getCompanyId())
                        .build()
        );

    }

}
