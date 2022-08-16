package com.example.shop.controller;

import com.example.shop.common.consts.ErrorConst;
import com.example.shop.common.exception.NotFoundException;
import com.example.shop.domain.account.Member;
import com.example.shop.domain.account.MemberRepository;
import com.example.shop.domain.product.*;
import com.example.shop.dto.common.ResponseSavedIdDto;
import com.example.shop.dto.product.*;
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
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductRepository productRepository;

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    @GetMapping
    public ResponseEntity<?> getProducts(@Valid RequestProductListDto requestListDto) {
        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.DESC, "prodRegDate");
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
                                                .prodId(product.getProdId())
                                                .prodTitle(product.getProdTitle())
                                                .prodSubtitle(product.getProdSubtitle())
                                                .prodPrice(product.getProdPrice())
                                                .prodStock(product.getProdStock())
                                                .prodCount(product.getProdCount())
                                                .prodWeight(product.getProdWeight())
                                                .prodMainImg(product.getProdMainImg())
                                                .prodSubImg(product.getProdSubImg())
                                                .prodRegDate(product.getProdRegDate())
                                                .build()
                                ).collect(Collectors.toList())
                        ).build()
        );

    }

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody RequestRegisterProductDto productDto) {

        Product savedProduct = productRepository.save(
                Product.builder()
                        .prodTitle(productDto.getProdTitle())
                        .prodSubtitle(productDto.getProdSubtitle())
                        .prodPrice(productDto.getProdPrice())
                        .prodStock(productDto.getProdStock())
                        .prodCount(productDto.getProdCount())
                        .prodRegDate(LocalDateTime.now())
                        .prodWeight(productDto.getProdWeight())
                        .prodMainImg(productDto.getProdMainImg())
                        .prodSubImg(productDto.getProdSubImg())
                        .build()
        );

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(savedProduct.getProdId())
                        .build()
        );
    }

    @DeleteMapping("{prodId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String prodId) {

        Product product = productRepository.findProductByProdId(prodId)
                        .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_DATA));

        productRepository.delete(product);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/review")
    public ResponseEntity<?> getReviews(@Valid RequestReviewListDto reviewListDto) {

        PageRequest pageRequest = PageRequest.of(reviewListDto.getPage(), reviewListDto.getPageSize(), Sort.Direction.DESC, "reviewRegDate");
        Specification<Review> specification = ReviewSpecification.getReviewListSpecification(reviewListDto);
        Page<Review> reviewPage = reviewRepository.findAll(specification, pageRequest);

        return ResponseEntity.ok(
                ResponseReviewListDto.builder()
                        .totalCount(reviewPage.getTotalElements())
                        .page(reviewPage.getNumber())
                        .pageSize(reviewPage.getSize())
                        .reviewListItems(
                                reviewPage.getContent().stream().map(
                                        review -> ResponseReviewListDto.ReviewListItem.builder()
                                                .reviewId(review.getReviewId())
                                                .reviewWriter(review.getMember().getMemId())
                                                .reviewQw(review.getReviewQw())
                                                .reviewTitle(review.getReviewTitle())
                                                .reviewContent(review.getReviewContent())
                                                .reviewImg(review.getReviewImg())
                                                .reviewRegDate(review.getReviewRegDate())
                                                .build()
                                ).collect(Collectors.toList())
                        )
                        .build()
        );
    }

/*    @PostMapping("/review")
    public ResponseEntity<?> registerReviews(@RequestBody RequestRegisterReviewDto reviewDto) {

        Member reviewWriter = memberRepository.findMemberByMemId(reviewDto.getReviewWriter())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_DATA));
    }*/
}
