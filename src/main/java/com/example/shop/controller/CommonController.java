package com.example.shop.controller;

import com.example.shop.domain.common.Category;
import com.example.shop.domain.common.CategoryRepository;
import com.example.shop.domain.product.CartRepository;
import com.example.shop.dto.common.RequestRegisterCategoryDto;
import com.example.shop.dto.common.ResponseCategoryListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommonController {

    private final CategoryRepository categoryRepository;

    @PostMapping("/category")
    public ResponseEntity<?> registerCategory(@RequestBody RequestRegisterCategoryDto registerCategoryDto) {
        Category category = Category.builder()
                .categoryName(registerCategoryDto.getCategoryName())
                .build();

        categoryRepository.save(category);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());

        return ResponseEntity.created(selfLink).build();
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategory() {

        List<Category> categoryList = categoryRepository.findAll();

        return ResponseEntity.ok(
                ResponseCategoryListDto.builder()
                        .categoryItems(
                                categoryList.stream().map(
                                        category -> ResponseCategoryListDto.CategoryItems.builder()
                                                .categoryId(category.getCategoryId())
                                                .categoryName(category.getCategoryName())
                                                .build()
                                ).collect(Collectors.toList())
                        )
                .build());
    }
}
