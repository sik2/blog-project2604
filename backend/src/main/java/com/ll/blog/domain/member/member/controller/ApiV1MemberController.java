package com.ll.blog.domain.member.member.controller;

import com.ll.blog.domain.member.member.dto.MemberDto;
import com.ll.blog.domain.member.member.entity.Member;
import com.ll.blog.domain.member.member.service.MemberService;
import com.ll.blog.global.rq.Rq;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {
    private final MemberService memberService;
    private final Rq rq;

    record MemberJoinReqBody(
            @NotBlank
            String username,
            @NotBlank
            String password,
            @NotBlank
            String nickname
    ) {
    }

    @GetMapping("/me")
    public MemberDto me() {
        Member member = memberService.findById(rq.getActor().getId()).get();

        return new MemberDto(member);
    }

    @DeleteMapping("/logout")
    public void logout() {
        rq.deleteCookie("accessToken");
        rq.deleteCookie("apiKey");
    }
}