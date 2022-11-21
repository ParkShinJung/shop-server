package com.example.shop.controller;

import com.example.shop.common.consts.ErrorConst;
import com.example.shop.common.exception.NotFoundException;
import com.example.shop.domain.info.Notice;
import com.example.shop.domain.info.NoticeRepository;
import com.example.shop.domain.info.NoticeSpecification;
import com.example.shop.domain.info.QnaRepository;
import com.example.shop.dto.Info.RequestNoticeListDto;
import com.example.shop.dto.Info.RequestRegisterNoticeDto;
import com.example.shop.dto.Info.ResponseNoticeDto;
import com.example.shop.dto.Info.ResponseNoticeListDto;
import jdk.javadoc.doclet.Reporter;
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
@RequestMapping({"info"})
public class InfoController {

    private final NoticeRepository noticeRepository;

    private final QnaRepository qnaRepository;

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
}
