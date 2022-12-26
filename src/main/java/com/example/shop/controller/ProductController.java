package com.example.shop.controller;

import com.example.shop.common.consts.ErrorConst;
import com.example.shop.common.exception.NotFoundException;
import com.example.shop.common.type.ProductStatus;
import com.example.shop.domain.account.Member;
import com.example.shop.domain.account.MemberRepository;
import com.example.shop.domain.common.Category;
import com.example.shop.domain.common.CategoryRepository;
import com.example.shop.domain.info.Qna;
import com.example.shop.domain.info.QnaSpecification;
import com.example.shop.domain.product.*;
import com.example.shop.dto.common.RequestListDto;
import com.example.shop.dto.product.*;
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
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping({"product"})
public class ProductController {

    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;

    private final CategoryRepository categoryRepository;

    private final OrdersRepository ordersRepository;


    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody RequestRegisterProductDto registerProductDto) {

        Category category = categoryRepository.findByCategoryId(registerProductDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_CATEGORY));

        Long totalPrice = null;
        Double rate = registerProductDto.getDiscountRate();

        if (rate == 0) {
            totalPrice = Math.round(registerProductDto.getPrice());
        } else {
            totalPrice = Math.round(registerProductDto.getPrice() - (registerProductDto.getDiscountRate() / 100 * registerProductDto.getPrice()));
        }

        Product product = Product.builder()
                .title(registerProductDto.getTitle())
                .subTitle(registerProductDto.getSubtitle())
                .price(Math.round(registerProductDto.getPrice()))
                .stock(registerProductDto.getStock())
                .count(registerProductDto.getCount())
                .regDateTime(LocalDateTime.now())
                .weight(registerProductDto.getWeight())
                .mainImg(registerProductDto.getMainImg())
                .subImg(registerProductDto.getSubImg())
                .discountRate(Math.round(registerProductDto.getDiscountRate()))
                .totalPrice(totalPrice)
                .productStatus(registerProductDto.getProductStatus())
                .category(category)
                .build();

        productRepository.save(product);

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
                                        .subtitle(product.getSubTitle())
                                        .price(product.getPrice())
                                        .stock(product.getStock())
                                        .count(product.getCount())
                                        .regDateTime(product.getRegDateTime())
                                        .modDate(product.getModDate())
                                        .weight(product.getWeight())
                                        .mainImg(product.getMainImg())
                                        .subImg(product.getSubImg())
                                        .discountRate(product.getDiscountRate())
                                        .totalPrice(product.getTotalPrice())
                                        .productStatus(product.getProductStatus())
                                        .categoryId(product.getCategory().getCategoryId())
                                        .categoryName(product.getCategory().getCategoryName())
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
                .subtitle(product.getSubTitle())
                .price(product.getPrice())
                .stock(product.getStock())
                .count(product.getCount())
                .regDateTime(product.getRegDateTime())
                .modDate(product.getModDate())
                .weight(product.getWeight())
                .mainImg(product.getMainImg())
                .subImg(product.getSubImg())
                .discountRate(product.getDiscountRate())
                .totalPrice(product.getTotalPrice())
                .productStatus(product.getProductStatus())
                .categoryId(product.getCategory().getCategoryId())
                .categoryName(product.getCategory().getCategoryName())
                .build());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProductInfo(@PathVariable String productId, @RequestBody RequestRegisterProductDto registerProductDto) {

        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        Category category = categoryRepository.findByCategoryId(registerProductDto.getCategoryId())
                        .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_CATEGORY));

        Long totalPrice = null;
        Double rate = registerProductDto.getDiscountRate();

        if (rate == 0) {
            totalPrice = Math.round(registerProductDto.getPrice());
        } else {
            totalPrice = Math.round(registerProductDto.getPrice() - (registerProductDto.getDiscountRate() / 100 * registerProductDto.getPrice()));
        }

        product.setTitle(registerProductDto.getTitle());
        product.setSubTitle(registerProductDto.getSubtitle());
        product.setPrice(Math.round(registerProductDto.getPrice()));
        product.setStock(registerProductDto.getStock());
        product.setCount(registerProductDto.getCount());
        product.setWeight(registerProductDto.getWeight());
        product.setMainImg(registerProductDto.getMainImg());
        product.setSubImg(registerProductDto.getSubImg());
        product.setDiscountRate(Math.round(registerProductDto.getDiscountRate()));
        product.setTotalPrice(totalPrice);
        product.setProductStatus(registerProductDto.getProductStatus());
        product.setCategory(category);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());

        return ResponseEntity.created(selfLink).build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProductInfo(@PathVariable String productId) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        productRepository.delete(product);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/review")
    public ResponseEntity<?> registerReview(@RequestBody RequestRegisterReviewDto requestRegisterReviewDto) {

        Product product = productRepository.findByProductId(requestRegisterReviewDto.getProductId())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        Member member = memberRepository.findByMemberId(requestRegisterReviewDto.getMemberId())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_MEMBER));

        Review review = Review.builder()
                .member(member)
                .title(requestRegisterReviewDto.getTitle())
                .content(requestRegisterReviewDto.getContent())
                .image(requestRegisterReviewDto.getImage())
                .startRating(requestRegisterReviewDto.getStarRating())
                .product(product)
                .regDateTime(LocalDateTime.now())
                .build();

        reviewRepository.save(review);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();
    }

    @GetMapping("/review")
    public ResponseEntity<?> getReviewList(@Valid RequestListDto requestListDto) {

        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.ASC, "regDateTime");
        Page<Review> reviewList = reviewRepository.findAll(pageRequest);

        return ResponseEntity.ok(ResponseReviewListDto.builder()
                .page(reviewList.getNumber())
                .pageSize(reviewList.getSize())
                .totalCount(reviewList.getTotalElements())
                .reviewItems(
                        reviewList.stream().map(
                                review -> ResponseReviewListDto.ReviewItems.builder()
                                        .reviewId(review.getReviewId())
                                        .memberId(review.getMember().getMemberId())
                                        .memberName(review.getMember().getMemberId())
                                        .title(review.getTitle())
                                        .content(review.getContent())
                                        .image(review.getImage())
                                        .productId(review.getProduct().getProductId())
                                        .productName(review.getProduct().getTitle())
                                        .starRating(review.getStartRating())
                                        .regDateTime(review.getRegDateTime())
                                        .modDateTime(review.getModDateTime())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build());
    }

    @GetMapping("/review/member/{memberId}")
    public ResponseEntity<?> getReviewListByMemberId(@PathVariable String memberId, @Valid RequestListDto requestListDto) {

        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.ASC, "regDateTime");
        Page<Review> reviewList = reviewRepository.findAllByMember_MemberId(memberId, pageRequest);

        return ResponseEntity.ok(ResponseReviewListDto.builder()
                .page(reviewList.getNumber())
                .pageSize(reviewList.getSize())
                .totalCount(reviewList.getTotalElements())
                .reviewItems(
                        reviewList.stream().map(
                                review -> ResponseReviewListDto.ReviewItems.builder()
                                        .reviewId(review.getReviewId())
                                        .memberId(review.getMember().getMemberId())
                                        .memberName(review.getMember().getName())
                                        .title(review.getTitle())
                                        .content(review.getContent())
                                        .image(review.getImage())
                                        .productId(review.getProduct().getProductId())
                                        .productName(review.getProduct().getTitle())
                                        .starRating(review.getStartRating())
                                        .regDateTime(review.getRegDateTime())
                                        .modDateTime(review.getModDateTime())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build());
    }

    @GetMapping("/review/product/{productId}")
    public ResponseEntity<?> getReviewListByProductId(@PathVariable String productId, @Valid RequestListDto requestListDto) {

        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.ASC, "regDateTime");
        Page<Review> reviewList = reviewRepository.findAllByProduct_ProductId(productId, pageRequest);

        return ResponseEntity.ok(ResponseReviewListDto.builder()
                .page(reviewList.getNumber())
                .pageSize(reviewList.getSize())
                .totalCount(reviewList.getTotalElements())
                .reviewItems(
                        reviewList.stream().map(
                                review -> ResponseReviewListDto.ReviewItems.builder()
                                        .reviewId(review.getReviewId())
                                        .memberId(review.getMember().getMemberId())
                                        .memberName(review.getMember().getName())
                                        .title(review.getTitle())
                                        .content(review.getContent())
                                        .image(review.getImage())
                                        .productId(review.getProduct().getProductId())
                                        .productName(review.getProduct().getTitle())
                                        .starRating(review.getStartRating())
                                        .regDateTime(review.getRegDateTime())
                                        .modDateTime(review.getModDateTime())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build());
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<?> getReviewDetail(@PathVariable String reviewId) {

        Review review = reviewRepository.findByReviewId(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_REVIEW));

        return ResponseEntity.ok(ResponseReviewDto.builder()
                .reviewId(review.getReviewId())
                .memberId(review.getMember().getMemberId())
                .memberName(review.getMember().getName())
                .title(review.getTitle())
                .content(review.getContent())
                .image(review.getImage())
                .productId(review.getProduct().getProductId())
                .productName(review.getProduct().getTitle())
                .starRating(review.getStartRating())
                .regDateTime(review.getRegDateTime())
                .modDateTime(review.getModDateTime())
                .build());

    }

    @PutMapping("/review/{reviewId}")
    public ResponseEntity<?> updateReviewInfo(@PathVariable String reviewId, @RequestBody RequestUpdateReviewDto requestUpdateReviewDto) {

        Review review = reviewRepository.findByReviewId(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_REVIEW));

        review.setTitle(requestUpdateReviewDto.getTitle());
        review.setContent(requestUpdateReviewDto.getContent());
        review.setImage(requestUpdateReviewDto.getImage());
        review.setModDateTime(LocalDateTime.now());
        review.setStartRating(requestUpdateReviewDto.getStarRating());

        reviewRepository.save(review);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<?> deleteReviewInfo(@PathVariable String reviewId) {

        Review review = reviewRepository.findByReviewId(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_REVIEW));

        reviewRepository.delete(review);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductListByCategoryId(@PathVariable String categoryId, @Valid RequestListDto requestListDto) {

        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.ASC, "regDateTime");
        Page<Product> productList = productRepository.findByCategory_CategoryId(
                categoryId,
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
                                        .subtitle(product.getSubTitle())
                                        .price(product.getPrice())
                                        .stock(product.getStock())
                                        .count(product.getCount())
                                        .regDateTime(product.getRegDateTime())
                                        .modDate(product.getModDate())
                                        .weight(product.getWeight())
                                        .mainImg(product.getMainImg())
                                        .subImg(product.getSubImg())
                                        .discountRate(product.getDiscountRate())
                                        .totalPrice(product.getTotalPrice())
                                        .productStatus(product.getProductStatus())
                                        .categoryId(product.getCategory().getCategoryId())
                                        .categoryName(product.getCategory().getCategoryName())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build());
    }

    @PutMapping("/status/{productId}")
    ResponseEntity<?> updateProductStatus(@PathVariable String productId, @RequestBody RequestUpdateStatusDto requestUpdateStatusDto) {

        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        product.setProductStatus(requestUpdateStatusDto.getProductStatus());

        productRepository.save(product);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();
    }

    @PostMapping("/order")
    ResponseEntity<?> registerProductOrder(@RequestBody RequestRegisterOrderDto registerOrderDto) {

        Member member = memberRepository.findMemberIdByMemberId(registerOrderDto.getMemberId())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_MEMBER));

        Orders newOrders = Orders.builder()
                .member(member)
                .name(registerOrderDto.getName())
                .contact(registerOrderDto.getContact())
                .address1(registerOrderDto.getAddress1())
                .address2(registerOrderDto.getAddress2())
                .zipcode(registerOrderDto.getZipcode())
                .payment(registerOrderDto.getPayment())
                .build();

        if (registerOrderDto.getProductItem().size() > 0) {
            List<OrdersProduct> contents = registerOrderDto.getProductItem().stream().map(
                    content -> {
                        Product product = productRepository.findByProductId(content.getProduct())
                                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));
                        return OrdersProduct.builder()
                                .product(product)
                                .amount(content.getAmount())
                                .build();
                    }
            ).collect(Collectors.toList());

            newOrders.setOrdersProducts(contents);
        }

        Orders orders = ordersRepository.save(newOrders);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();

    }
}
