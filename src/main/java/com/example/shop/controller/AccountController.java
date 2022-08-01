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
                        .companyId(requestDto.getCompanyId())
                        .companyPw(requestDto.getCompanyPw())
                        .companyName(requestDto.getCompanyName())
                        .companyCeo(requestDto.getCompanyCeo())
                        .companyNumber(requestDto.getCompanyNumber())
                        .companyAddress1(requestDto.getCompanyAddress1())
                        .companyAddress2(requestDto.getCompanyAddress2())
                        .companyEmail(requestDto.getCompanyEmail())
                        .build());

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(savedCompany.getCompanyId())
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
                        .memberItems(
                                memberPage.getContent().stream().map(
                                        member -> ResponseMemberListDto.MemberListItem.builder()
                                                .memberId(member.getMemberId())
                                                .memberPw(member.getMemberPw())
                                                .memberName(member.getMemberName())
                                                .memberAddress1(member.getMemberAddress1())
                                                .memberAddress2(member.getMemberAddress2())
                                                .memberNumber(member.getMemberNumber())
                                                .memberBirthday(member.getMemberBirthday())
                                                .memberRegDate(member.getMemberRegDate())
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
                        .memberId(memberDto.getMemberId())
                        .memberPw(memberDto.getMemberPw())
                        .memberName(memberDto.getMemberName())
                        .memberAddress1(memberDto.getMemberAddress1())
                        .memberAddress2(memberDto.getMemberAddress2())
                        .memberNumber(memberDto.getMemberNumber())
                        .memberRegDate(LocalDateTime.now())
                        .memberBirthday(LocalDate.parse(memberDto.getMemberBirthday(), DateFormatConst.DATE_FORMAT))
                        .build());

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(
                ResponseSavedIdDto.builder()
                        .savedId(savedMember.getMemberId())
                        .build()
        );
    }

}
