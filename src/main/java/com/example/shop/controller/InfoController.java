package com.example.shop.controller;

import com.example.shop.domain.info.Notice;
import com.example.shop.domain.info.NoticeRepository;
import com.example.shop.dto.common.ResponseSavedIdDto;
import com.example.shop.dto.info.RequestRegisterNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("info")
public class InfoController {

    private final NoticeRepository noticeRepository;

    @PostMapping
    public ResponseEntity<?> registerNotice(@RequestBody RequestRegisterNoticeDto noticeDto) {

        Notice notice = noticeRepository.save(
                Notice.builder()
                        .notWriter(noticeDto.getNotWriter())
                        .notPw(noticeDto.getNotPw())
                        .notTitle(noticeDto.getNotTitle())
                        .notContent(noticeDto.getNotContent())
                        .notFile(noticeDto.getNotFile())
                        .notRegDate(LocalDateTime.now())
                        .build()
        );

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(notice.getNotId())
                        .build()
        );
    }
}
