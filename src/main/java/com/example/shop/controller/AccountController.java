package com.example.shop.controller;

import com.example.shop.common.consts.DateFormatConst;
import com.example.shop.common.consts.ErrorConst;
import com.example.shop.common.exception.NotFoundException;
import com.example.shop.domain.account.*;
import com.example.shop.dto.account.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping({"account"})
public class AccountController {

    private final CompanyRepository companyRepository;

    @Autowired
    private final MemberRepository memberRepository;


    @PostMapping("company")
    public ResponseEntity<?> registerCompanyInfo(@RequestBody RequestRegisterCompanyDto registerCompanyDto) {
/*        Company company = companyRepository.countById()
                .orElse(
                        Company.builder()
                                .companyName(registerCompanyDto.getCompanyName())
                                .ceoName(registerCompanyDto.getCeoName())
                                .contact(registerCompanyDto.getContact())
                                .address1(registerCompanyDto.getAddress1())
                                .address2(registerCompanyDto.getAddress2())
                                .email(registerCompanyDto.getEmail())
                                .build()

                );


        Company company = Company.builder()
                .companyName(registerCompanyDto.getCompanyName())
                .ceoName(registerCompanyDto.getCeoName())
                .contact(registerCompanyDto.getContact())
                .address1(registerCompanyDto.getAddress1())
                .address2(registerCompanyDto.getAddress2())
                .email(registerCompanyDto.getEmail())
                .build();

        companyRepository.save(company);*/

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());

        return ResponseEntity.created(selfLink).build();
    }

    @PostMapping("member")
    @Transactional
    public ResponseEntity<?> getMemberList(@RequestBody RequestRegisterMemberDto registerMemberDto) {

/*        String rawPassword = registerMemberDto.getMemberPassword();
        String encryptedPassword = new BCryptPasswordEncoder(8).encode(rawPassword);
        registerMemberDto.setMemberPassword(encryptedPassword);*/
        Member member = Member.builder()
                .memberId(registerMemberDto.getMemberId())
                .password(registerMemberDto.getPassword())
                .name(registerMemberDto.getName())
                .zipCode(registerMemberDto.getZipCode())
                .address1(registerMemberDto.getAddress1())
                .address2(registerMemberDto.getAddress2())
                .contact(registerMemberDto.getContact())
                .birthday(registerMemberDto.getBirthday())
                .accountType(registerMemberDto.getAccountType())
                .email(registerMemberDto.getEmail())
                .modDate(LocalDateTime.now())
                .regDate(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());

        return ResponseEntity.created(selfLink).build();
    }

    @GetMapping("/member")
    public ResponseEntity<?> getMemberList(@Valid RequestMemberListDto requestListDto) {

        PageRequest pageRequest = PageRequest.of(requestListDto.getPage(), requestListDto.getPageSize(), Sort.Direction.ASC, "regDate");
        Page<Member> memberList = memberRepository.findAll(
                MemberSpecification.getMemberSpecification(requestListDto),
                pageRequest
        );

        return ResponseEntity.ok(ResponseMemberListDto.builder()
                .page(memberList.getNumber())
                .pageSize(memberList.getSize())
                .totalCount(memberList.getTotalElements())
                .memberItemsList(
                        memberList.stream().map(
                                member -> ResponseMemberListDto.MemberItems.builder()
                                        .id(member.getId())
                                        .memberId(member.getMemberId())
                                        .password(member.getPassword())
                                        .name(member.getName())
                                        .zipCode(member.getZipCode())
                                        .address1(member.getAddress1())
                                        .address2(member.getAddress2())
                                        .contact(member.getContact())
                                        .email(member.getEmail())
                                        .birthday(member.getBirthday())
                                        .accountType(member.getAccountType())
                                        .modDate(member.getModDate())
                                        .regDate(member.getRegDate())
                                        .build()
                        ).collect(Collectors.toList())
                )
                .build());
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> getMemberDetail(@PathVariable String memberId) {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_MEMBER));

        return ResponseEntity.ok(ResponseMemberDto.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .password(member.getPassword())
                .name(member.getName())
                .zipCode(member.getZipCode())
                .address1(member.getAddress1())
                .address2(member.getAddress2())
                .contact(member.getContact())
                .email(member.getEmail())
                .accountType(member.getAccountType())
                .birthday(member.getBirthday())
                .modDate(member.getModDate())
                .regDate(member.getRegDate())
                .build());
    }

    @PutMapping("/member/{memberId}")
    public ResponseEntity<?> updateMemberInfo(@PathVariable String memberId, @RequestBody RequestRegisterMemberDto registerMemberDto) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_MEMBER));

        member.setMemberId(registerMemberDto.getMemberId());
        member.setPassword(registerMemberDto.getPassword());
        member.setName(registerMemberDto.getName());
        member.setZipCode(registerMemberDto.getZipCode());
        member.setAddress1(registerMemberDto.getAddress1());
        member.setAddress2(registerMemberDto.getAddress2());
        member.setContact(registerMemberDto.getContact());
        member.setEmail(registerMemberDto.getEmail());
        member.setBirthday(registerMemberDto.getBirthday());
        member.setAccountType(registerMemberDto.getAccountType());
        member.setModDate(LocalDateTime.now());

        memberRepository.save(member);

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
        return ResponseEntity.created(selfLink).build();

    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<?> deleteMemberInfo(@PathVariable String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorConst.NOT_FOUND_MEMBER));

        memberRepository.delete(member);

        return ResponseEntity.noContent().build();

    }
    @GetMapping("/signup/id/{memberId}")
    public ResponseEntity<?> getDuplicateMemberId(@PathVariable String memberId) {
        List<Member> member = memberRepository.findAll();
        int count = 0;
        List<String> members = new ArrayList<>();
        Boolean result;
        try {
            member.forEach(aaa -> {
                String memberIds = aaa.getMemberId();
                members.add(memberIds);
            });
            count = Collections.frequency(members, memberId);

            if (count == 0) {
                result = true;
            } else {
                result = false;
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Exception("잘못된 요청입니다."));
        }

/*        try {
            memberRepository.findByMemberId(memberId);
        } catch (DuplicateKeyException ignored) {
            log.error("중복된 아이디입니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Exception("잘못된 요청입니다."));
        }*/
        return ResponseEntity.ok(result);
    }
    private LocalDate transformDate(String sourceDate) {
        LocalDate targetDate = null;
        if (StringUtils.isNotEmpty(sourceDate)) {
            targetDate = LocalDate.parse(sourceDate, DateFormatConst.DATE_FORMAT);
        }
        return targetDate;
    }
}
