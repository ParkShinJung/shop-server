package com.example.shop.controller;

import com.example.shop.common.consts.DateFormatConst;
import com.example.shop.domain.account.*;
import com.example.shop.dto.accont.RequestMemberListDto;
import com.example.shop.dto.accont.RequestRegisterCompanyDto;
import com.example.shop.dto.accont.RequestRegisterMemberDto;
import com.example.shop.dto.accont.ResponseMemberListDto;
import com.example.shop.dto.common.ResponseSavedIdDto;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final CompanyRepository companyRepository;

    private final MemberRepository memberRepository;

    @PostMapping("/company")
    public ResponseEntity<?> registerCompany(@RequestBody RequestRegisterCompanyDto requestDto) {

        Company savedCompany = companyRepository.save(
                Company.builder()
                        .comId(requestDto.getComId())
                        .comPw(requestDto.getComPw())
                        .comName(requestDto.getComName())
                        .comCeo(requestDto.getComCeo())
                        .comNumber(requestDto.getComNumber())
                        .comAddress1(requestDto.getComAddress1())
                        .comAddress2(requestDto.getComAddress2())
                        .comEmail(requestDto.getComEmail())
                        .build());

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(savedCompany.getComId())
                        .build()
        );
    }

    @GetMapping("/member")
    public ResponseEntity<?> getMembers(@Valid RequestMemberListDto requestListDto) {
        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.DESC, "memberRegDate");
        Specification<Member> specification = MemberSpecification.getMemberListSpecification(requestListDto);
        Page<Member> memberPage = memberRepository.findAll(specification, pageRequest);

        return ResponseEntity.ok(
                ResponseMemberListDto.builder()
                        .totalCount(memberPage.getTotalElements())
                        .page(memberPage.getNumber())
                        .pageSize(memberPage.getSize())
                        .memItems(
                                memberPage.getContent().stream().map(
                                        member -> ResponseMemberListDto.MemberListItem.builder()
                                                .memId(member.getMemId())
                                                .memPw(member.getMemPw())
                                                .memName(member.getMemName())
                                                .memAddress1(member.getMemAddress1())
                                                .memAddress2(member.getMemAddress2())
                                                .memNumber(member.getMemNumber())
                                                .memBirthday(member.getMemBirthday())
                                                .memRegDate(member.getMemRegDate())
                                                .build()
                                ).collect(Collectors.toList())
                        )
                        .build()
        );
    }

    @PostMapping("/member")
    public ResponseEntity<?> registerMember(@RequestBody RequestRegisterMemberDto memberDto) {

        Member savedMember = memberRepository.save(
                Member.builder()
                        .memId(memberDto.getMemId())
                        .memPw(memberDto.getMemPw())
                        .memName(memberDto.getMemName())
                        .memAddress1(memberDto.getMemAddress1())
                        .memAddress2(memberDto.getMemAddress2())
                        .memNumber(memberDto.getMemNumber())
                        .memRegDate(LocalDateTime.now())
                        .memBirthday(LocalDate.parse(memberDto.getMemBirthday(), DateFormatConst.DATE_FORMAT))
                        .build());

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(savedMember.getMemId())
                        .build()
        );
    }

}
