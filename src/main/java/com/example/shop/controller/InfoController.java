package com.example.shop.controller;

import com.example.shop.common.consts.ErrorConst;
import com.example.shop.common.exception.NotFoundException;
import com.example.shop.common.type.QnaStatus;
import com.example.shop.domain.account.Member;
import com.example.shop.domain.account.MemberRepository;
import com.example.shop.domain.info.*;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductRepository;
import com.example.shop.dto.Info.*;
import com.example.shop.dto.common.RequestListDto;
import jdk.javadoc.doclet.Reporter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping({"info"})
public class InfoController {

    private final NoticeRepository noticeRepository;

    private final QnaRepository qnaRepository;

    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;

    @PostMapping("/notice")
    public ResponseEntity<?> registerNotice(@RequestBody RequestRegisterNoticeDto registerNoticeDto) {

        Notice notice = Notice.builder()
                .writer(registerNoticeDto.getWriter())
                .password(registerNoticeDto.getPassword())
                .title(registerNoticeDto.getTitle())
                .content(registerNoticeDto.getContent())
                .file(registerNoticeDto.getFile())
                .regDateTime(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();
    }

    @GetMapping("/notice")
    public ResponseEntity<?> getNoticeList(@Valid RequestNoticeListDto requestNoticeListDto) {

        PageRequest pageRequest = PageRequest.of(requestNoticeListDto.getPage(), requestNoticeListDto.getPageSize(), Sort.Direction.ASC, "regDateTime");
        Page<Notice> noticeList = noticeRepository.findAll(
                NoticeSpecification.getNoticeSpecification(requestNoticeListDto),
                pageRequest
        );

       return ResponseEntity.ok(ResponseNoticeListDto.builder()
                       .page(noticeList.getNumber())
                       .pageSize(noticeList.getSize())
                       .totalCount(noticeList.getTotalElements())
                       .memberItemsList(
                               noticeList.stream().map(
                                       notice -> ResponseNoticeListDto.NoticeItems.builder()
                                               .noticeId(notice.getNoticeId())
                                               .writer(notice.getWriter())
                                               .password(notice.getPassword())
                                               .title(notice.getTitle())
                                               .content(notice.getContent())
                                               .file(notice.getFile())
                                               .regDateTime(notice.getRegDateTime())
                                               .build()
                               ).collect(Collectors.toList())
                       )
               .build());
    }

    @GetMapping("/notice/{noticeId}")
    public ResponseEntity<?> getNoticeDetail(@PathVariable String noticeId) {

        Notice notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_NOTICE));

        return ResponseEntity.ok(ResponseNoticeDto.builder()
                .noticeId(notice.getNoticeId())
                .writer(notice.getWriter())
                .password(notice.getPassword())
                .title(notice.getTitle())
                .content(notice.getContent())
                .file(notice.getFile())
                .regDateTime(notice.getRegDateTime())
                .build());
    }

    @PutMapping("/notice/{noticeId}")
    public ResponseEntity<?> updateNoticeInfo(@PathVariable String noticeId, @RequestBody RequestRegisterNoticeDto registerNoticeDto) {

        Notice notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_NOTICE));

        notice.setWriter(registerNoticeDto.getWriter());
        notice.setPassword(registerNoticeDto.getPassword());
        notice.setTitle(registerNoticeDto.getTitle());
        notice.setContent(registerNoticeDto.getContent());
        notice.setFile(registerNoticeDto.getFile());

        noticeRepository.save(notice);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();
    }

    @DeleteMapping("/notice/{noticeId}")
    public ResponseEntity<?> deleteNoticeInfo(@PathVariable String noticeId) {

        Notice notice = noticeRepository.findByNoticeId(noticeId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_NOTICE));

        noticeRepository.delete(notice);

        return ResponseEntity.noContent().build();
    }


    @PostMapping("/qna")
    public ResponseEntity<?> registerQna(@RequestBody RequestRegisterQnaDto registerQnaDto) {

        Product product = productRepository.findByProductId(registerQnaDto.getProductId())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_PRODUCT));

        Member member = memberRepository.findById(registerQnaDto.getMemberId())
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_MEMBER));

        Qna qna = Qna.builder()
                .product(product)
                .member(member)
                .password(registerQnaDto.getPassword())
                .title(registerQnaDto.getTitle())
                .content(registerQnaDto.getContent())
                .file(registerQnaDto.getFile())
                .regDateTime(LocalDateTime.now())
                .secret(registerQnaDto.getSecret())
                .qnaStatus(QnaStatus.before)
                .build();

        qnaRepository.save(qna);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();
    }

    @GetMapping("/qna")
    public ResponseEntity<?> getQnaList(@Valid RequestQnaListDto requestQnaListDto) {

        PageRequest pageRequest = PageRequest.of(requestQnaListDto.getPage(), requestQnaListDto.getPageSize(), Sort.Direction.ASC, "regDateTime");
        Page<Qna> qnaList = qnaRepository.findAll(
                QnaSpecification.getQnaSpecification(requestQnaListDto),
                pageRequest
        );

        return ResponseEntity.ok(ResponseQnaListDto.builder()
                .page(qnaList.getNumber())
                .pageSize(qnaList.getSize())
                .totalCount(qnaList.getTotalElements())
                .noticeItems(
                        qnaList.stream().map(
                                qna -> ResponseQnaListDto.QnaItems.builder()
                                        .productId(qna.getProduct().getProductId())
                                        .productName(qna.getProduct().getTitle())
                                        .memberId(qna.getMember().getMemberId())
                                        .memberName(qna.getMember().getMemberName())
                                        .password(qna.getPassword())
                                        .title(qna.getTitle())
                                        .content(qna.getContent())
                                        .file(qna.getFile())
                                        .regDateTime(qna.getRegDateTime())
                                        .secret(qna.getSecret())
                                        .qnaStatus(qna.getQnaStatus())
                                        .build()

                        ).collect(Collectors.toList())
                )
                .build());
    }

    @GetMapping("/qna/{qnaId}")
    public ResponseEntity<?> getQnaDetail(@PathVariable String qnaId) {

        Qna qna = qnaRepository.findByQnaId(qnaId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_QNA));

        return ResponseEntity.ok(ResponseQnaDto.builder()
                .productId(qna.getProduct().getProductId())
                .productName(qna.getProduct().getTitle())
                .memberId(qna.getMember().getId())
                .memberName(qna.getMember().getMemberName())
                .password(qna.getPassword())
                .title(qna.getTitle())
                .content(qna.getContent())
                .file(qna.getFile())
                .regDateTime(qna.getRegDateTime())
                .secret(qna.getSecret())
                .qnaStatus(qna.getQnaStatus())
                .build());
    }

    @GetMapping("/qna/{productId}")
    public ResponseEntity<?> getQnaListByProduct(@PathVariable String productId, @Valid RequestListDto requestListDto) {

        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.DESC, "regDateTime");
        Page<Qna> qnaList = qnaRepository.findAllByProduct_ProductId(productId, pageRequest);

        return ResponseEntity.ok(ResponseQnaListDto.builder()
                .page(qnaList.getNumber())
                .pageSize(qnaList.getSize())
                .totalCount(qnaList.getTotalElements())
                .noticeItems(
                        qnaList.stream().map(
                                qna -> ResponseQnaListDto.QnaItems.builder()
                                        .productId(qna.getProduct().getProductId())
                                        .productName(qna.getProduct().getTitle())
                                        .memberId(qna.getMember().getMemberId())
                                        .memberName(qna.getMember().getMemberName())
                                        .password(qna.getPassword())
                                        .title(qna.getTitle())
                                        .content(qna.getContent())
                                        .file(qna.getFile())
                                        .regDateTime(qna.getRegDateTime())
                                        .secret(qna.getSecret())
                                        .qnaStatus(qna.getQnaStatus())
                                        .build()

                        ).collect(Collectors.toList())
                )
                .build());
    }

    @GetMapping("/qna/{memberId}")
    public ResponseEntity<?> getQnaListByMember(@PathVariable String memberId, @Valid RequestListDto requestListDto) {

        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.DESC, "regDateTime");
        Page<Qna> qnaList = qnaRepository.findAllByMember_MemberId(memberId, pageRequest);

        return ResponseEntity.ok(ResponseQnaListDto.builder()
                .page(qnaList.getNumber())
                .pageSize(qnaList.getSize())
                .totalCount(qnaList.getTotalElements())
                .noticeItems(
                        qnaList.stream().map(
                                qna -> ResponseQnaListDto.QnaItems.builder()
                                        .productId(qna.getProduct().getProductId())
                                        .productName(qna.getProduct().getTitle())
                                        .memberId(qna.getMember().getMemberId())
                                        .memberName(qna.getMember().getMemberName())
                                        .password(qna.getPassword())
                                        .title(qna.getTitle())
                                        .content(qna.getContent())
                                        .file(qna.getFile())
                                        .regDateTime(qna.getRegDateTime())
                                        .secret(qna.getSecret())
                                        .qnaStatus(qna.getQnaStatus())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build());
    }

    @PutMapping("/qna/{qnaId}")
    public ResponseEntity<?> updateQnaInfo(@PathVariable String qnaId, @RequestBody RequestUpdateQnaDto requestUpdateQnaDto) {

        Qna qna = qnaRepository.findByQnaId(qnaId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_QNA));

        qna.setPassword(requestUpdateQnaDto.getPassword());
        qna.setTitle(requestUpdateQnaDto.getTitle());
        qna.setContent(requestUpdateQnaDto.getContent());
        qna.setFile(requestUpdateQnaDto.getFile());
        qna.setSecret(requestUpdateQnaDto.getSecret());

        qnaRepository.save(qna);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();

    }

    @DeleteMapping("/qna/{qnaId}")
    public ResponseEntity<?> deleteQnaInfo(@PathVariable String qnaId) {

        Qna qna = qnaRepository.findByQnaId(qnaId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_QNA));

        qnaRepository.delete(qna);

        return ResponseEntity.noContent().build();
    }
}
