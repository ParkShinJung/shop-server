package com.example.shop.controller;

import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductRepository;
import com.example.shop.dto.common.ResponseSavedIdDto;
import com.example.shop.dto.product.RequestRegisterProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody RequestRegisterProductDto productDto) {

        Product savedProduct = productRepository.save(
                Product.builder()
                        .productTitle(productDto.getProductTitle())
                        .productSubtitle(productDto.getProductSubtitle())
                        .productPrice(productDto.getProductPrice())
                        .productStock(productDto.getProductStock())
                        .productCount(productDto.getProductCount())
                        .productRegDate(LocalDateTime.now())
                        .productWeight(productDto.getProductWeight())
                        .productMainImg(productDto.getProductMainImg())
                        .productSubImg(productDto.getProductSubImg())
                        .build()
        );

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(savedProduct.getProductId())
                        .build()
        );
    }
}
