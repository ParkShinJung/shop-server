package com.example.shop.controller;

import com.example.shop.common.consts.ErrorConst;
import com.example.shop.common.exception.NotFoundException;
import com.example.shop.domain.account.Member;
import com.example.shop.domain.account.MemberRepository;
import com.example.shop.domain.product.*;
import com.example.shop.dto.common.ResponseSavedIdDto;
import com.example.shop.dto.product.*;
import com.fasterxml.classmate.types.ResolvedInterfaceType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import javax.xml.ws.Response;
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

    private final OrderRepository orderRepository;

    private final CartRepository cartRepository;

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

    @PutMapping({"prodId"})
    public ResponseEntity<?> putProduct(@PathVariable String prodId, @RequestBody RequestRegisterProductDto productDto) {
        Product product = productRepository.findProductByProdId(prodId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        product.setProdTitle(productDto.getProdTitle());
        product.setProdSubtitle(productDto.getProdSubtitle());
        product.setProdPrice(productDto.getProdPrice());
        product.setProdStock(productDto.getProdStock());
        product.setProdCount(productDto.getProdCount());
        product.setProdWeight(productDto.getProdWeight());
        product.setProdMainImg(productDto.getProdMainImg());
        product.setProdSubImg(productDto.getProdSubImg());
        product.setProdModDate(LocalDateTime.now());

        productRepository.save(product);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();
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

    @PostMapping("/review")
    public ResponseEntity<?> registerReviews(@RequestBody RequestRegisterReviewDto reviewDto) {
        Member member = memberRepository.findByMemId(reviewDto.getMember())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_MEMBER));

        Order order = orderRepository.findByOrdId(reviewDto.getOrder())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_ORDER));

        Review review = reviewRepository.save(
                Review.builder()
                        .member(member)
                        .reviewQw(reviewDto.getReviewQw())
                        .reviewTitle(reviewDto.getReviewTitle())
                        .reviewContent(reviewDto.getReviewContent())
                        .reviewImg(reviewDto.getReviewImg())
                        .order(order)
                        .prodId(order.getProduct().getProdId())
                        .reviewRegDate(LocalDateTime.now())
                        .build()
        );

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(review.getReviewId())
                        .build()
        );
    }
    @PutMapping("/review/{reviewId}")
    public ResponseEntity<?> putReviews(@PathVariable String reviewId, @RequestBody RequestRegisterReviewDto reviewDto) {
        Review review = reviewRepository.findReviewByReviewId(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_REVIEW));

        Member member = memberRepository.findByMemId(reviewDto.getMember())
                        .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_MEMBER));

        Order order = orderRepository.findByOrdId(reviewDto.getOrder())
                        .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_ORDER));

        review.setMember(member);
        review.setReviewQw(reviewDto.getReviewQw());
        review.setReviewTitle(reviewDto.getReviewTitle());
        review.setReviewContent(reviewDto.getReviewContent());
        review.setReviewImg(reviewDto.getReviewImg());
        review.setOrder(order);
        review.setProdId(order.getProduct().getProdId());
        reviewRepository.save(review);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();

    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable String reviewId) {

        Review review = reviewRepository.findReviewByReviewId(reviewId)
                        .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_REVIEW));

        reviewRepository.delete(review);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cart")
    public ResponseEntity<?> getCart(@Valid RequestCartListDto cartListDto) {
        PageRequest pageRequest = PageRequest.of(cartListDto.getPage(), cartListDto.getPageSize(), Sort.Direction.DESC, "cartId");
        Specification<Cart> specification = CartSpecification.getCartListSpecification(cartListDto);
        Page<Cart> cartPage = cartRepository.findAll(specification, pageRequest);

        return ResponseEntity.ok(
                ResponseCartListDto.builder()
                        .totalCount(cartPage.getTotalElements())
                        .page(cartPage.getNumber())
                        .pageSize(cartPage.getSize())
                        .cartListItems(
                                cartPage.getContent().stream().map(
                                        cart -> ResponseCartListDto.CartListItem.builder()
                                                .cartId(cart.getCartId())
                                                .member(cart.getMember().getMemId())
                                                .product(cart.getProduct().getProdId())
                                                .cartQuantity(cart.getCartQuantity())
                                                .build()
                                ).collect(Collectors.toList())
                        )
                        .build()
        );
    }

    @GetMapping("/cart/{prodId}")
    public ResponseEntity<?> getCartByProdId(@PathVariable String prodId, @Valid RequestCartListDto cartListDto) {

        Product product = productRepository.findByProdId(prodId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        PageRequest pageRequest = PageRequest.of(cartListDto.getPage(), cartListDto.getPageSize(), Sort.Direction.DESC, "cartId");
        Specification<Cart> specification = CartSpecification.getCartListSpecification(cartListDto);
        Page<Cart> cartPage = cartRepository.findAll(specification, pageRequest);

        return ResponseEntity.ok(
                ResponseCartListDto.builder()
                        .totalCount(cartPage.getTotalElements())
                        .page(cartPage.getNumber())
                        .pageSize(cartPage.getSize())
                        .cartListItems(
                                cartPage.getContent().stream().map(
                                        cart -> ResponseCartListDto.CartListItem.builder()
                                                .cartId(cart.getCartId())
                                                .member(cart.getMember().getMemId())
                                                .product(product.getProdId())
                                                .cartQuantity(cart.getCartQuantity())
                                                .build()
                                ).collect(Collectors.toList())
                        )
                        .build()
        );
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable String cartId) {

        Cart cart = cartRepository.findCartByCartId(cartId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_CART));

        cartRepository.delete(cart);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cart")
    public ResponseEntity<?> registerCart(@RequestBody RequestRegisterCartDto cartDto) {
        Member member = memberRepository.findByMemId(cartDto.getMember())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_MEMBER));

        Product product = productRepository.findByProdId(cartDto.getProduct())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        Cart cart = cartRepository.save(
                Cart.builder()
                        .member(member)
                        .product(product)
                        .cartQuantity(cartDto.getCartQuantity())
                        .build()
        );

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(cart.getCartId())
                        .build()
        );
    }

/*    @PostMapping("/order")
    public ResponseEntity<?> registerOrder(@RequestBody Reque) {


    }*/


}
