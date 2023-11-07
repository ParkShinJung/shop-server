package com.example.shop.controller;

import com.example.shop.common.consts.ErrorConst;
import com.example.shop.domain.Tests;
import com.example.shop.domain.TestsRepository;
import com.example.shop.dto.RequestRegisterBoardDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestsRepository testsRepository;

    @Operation(summary = "test hello", description = "hello api example")
    @GetMapping("/hello")
    public ResponseEntity<String> hello(@Parameter(description = "이름", required = true, example = "Park") @RequestParam String name) {
        return ResponseEntity.ok("hello " + name);
    }

    @PostMapping("/tests")
    public ResponseEntity<?> registerTests(@RequestBody RequestRegisterBoardDto requestDto) {

        Tests savedTests = testsRepository.save(
                Tests.builder()
                       .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .subtitle(requestDto.getSubtitle())
                        .price(requestDto.getPrice())
                        .build());

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(null);
    }
}