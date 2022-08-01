package com.example.shop.controller;

import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductRepository;
import com.example.shop.domain.product.ProductSpecification;
import com.example.shop.dto.accont.ResponseMemberListDto;
import com.example.shop.dto.common.ResponseSavedIdDto;
import com.example.shop.dto.product.RequestProductListDto;
import com.example.shop.dto.product.RequestRegisterProductDto;
import com.example.shop.dto.product.ResponseProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<?> getProducts(@Valid RequestProductListDto requestListDto) {
        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.DESC, "productRegDate");
        Specification<Product> specification = ProductSpecification.getProductListSpecification(requestListDto);
        Page<Product> productPage = productRepository.findAll(specification, pageRequest);

        return ResponseEntity.ok(
                ResponseProductListDto.builder()
                        .totalCount(productPage.getTotalElements())
                        .page(productPage.getNumber())
                        .pageSize(productPage.getSize())
                        .productItems(
                                productPage.getContent().stream().map(
                                        product -> ResponseProductListDto.ProductListItem.builder()
                                                .productTitle(product.getProductTitle())
                                                .productSubtitle(product.getProductSubtitle())
                                                .productPrice(product.getProductPrice())
                                                .productStock(product.getProductStock())
                                                .productCount(product.getProductCount())
                                                .productWeight(product.getProductWeight())
                                                .productMainImg(product.getProductMainImg())
                                                .productSubImg(product.getProductSubImg())
                                                .productRegDate(product.getProductRegDate())
                                                .build()
                                ).collect(Collectors.toList())
                        ).build()
        );

    }

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
