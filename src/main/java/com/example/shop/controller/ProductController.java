package com.example.shop.controller;

import com.example.shop.common.consts.ErrorConst;
import com.example.shop.common.exception.NotFoundException;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductRepository;
import com.example.shop.domain.product.ProductSpecification;
import com.example.shop.dto.product.RequestProductListDto;
import com.example.shop.dto.product.RequestRegisterProductDto;
import com.example.shop.dto.product.ResponseProductDto;
import com.example.shop.dto.product.ResponseProductListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping({"product"})
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody RequestRegisterProductDto registerProductDto) {

        Product product = Product.builder()
                .title(registerProductDto.getTitle())
                .subtitle(registerProductDto.getSubtitle())
                .price(registerProductDto.getPrice())
                .stock(registerProductDto.getStock())
                .count(registerProductDto.getCount())
                .regDateTime(LocalDateTime.now())
                .weight(registerProductDto.getWeight())
                .mainImg(registerProductDto.getMainImg())
                .subImg(registerProductDto.getSubImg())
                .discountRate(registerProductDto.getDiscountRate())
                .discountPrice(registerProductDto.getDiscountPrice())
                .build();

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();
    }

    @GetMapping
    public ResponseEntity<?> getProductList(@Valid RequestProductListDto requestProductListDto) {

        PageRequest pageRequest = PageRequest.of(requestProductListDto.getPage(), requestProductListDto.getPageSize(), Sort.Direction.ASC, "regDateTime");
        Page<Product> productList = productRepository.findAll(
                ProductSpecification.getProductSpecification(requestProductListDto),
                pageRequest
        );

        return ResponseEntity.ok(ResponseProductListDto.builder()
                .page(productList.getNumber())
                .pageSize(productList.getSize())
                .totalCount(productList.getTotalElements())
                .productItems(
                        productList.stream().map(
                                product -> ResponseProductListDto.ProductItems.builder()
                                        .productId(product.getProductId())
                                        .title(product.getTitle())
                                        .subtitle(product.getSubtitle())
                                        .price(product.getPrice())
                                        .stock(product.getStock())
                                        .count(product.getCount())
                                        .regDateTime(product.getRegDateTime())
                                        .modDate(product.getModDate())
                                        .weight(product.getWeight())
                                        .mainImg(product.getMainImg())
                                        .subImg(product.getSubImg())
                                        .discountRate(product.getDiscountRate())
                                        .discountPrice(product.getDiscountPrice())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build());
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProductDetail(@PathVariable String productId) {

        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        return ResponseEntity.ok(ResponseProductDto.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .subtitle(product.getSubtitle())
                .price(product.getPrice())
                .stock(product.getStock())
                .count(product.getCount())
                .regDateTime(product.getRegDateTime())
                .modDate(product.getModDate())
                .weight(product.getWeight())
                .mainImg(product.getMainImg())
                .subImg(product.getSubImg())
                .discountRate(product.getDiscountRate())
                .discountPrice(product.getDiscountPrice())
                .build());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProductInfo(@PathVariable String productId, @RequestBody RequestRegisterProductDto registerProductDto) {

        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        product.setTitle(registerProductDto.getTitle());
        product.setSubtitle(registerProductDto.getSubtitle());
        product.setPrice(registerProductDto.getPrice());
        product.setStock(registerProductDto.getStock());
        product.setCount(registerProductDto.getCount());
        product.setWeight(registerProductDto.getWeight());
        product.setMainImg(registerProductDto.getMainImg());
        product.setSubImg(registerProductDto.getSubImg());
        product.setDiscountRate(registerProductDto.getDiscountRate());
        product.setDiscountPrice(registerProductDto.getDiscountPrice());

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());

        return ResponseEntity.created(selfLink).build();

    }
}
