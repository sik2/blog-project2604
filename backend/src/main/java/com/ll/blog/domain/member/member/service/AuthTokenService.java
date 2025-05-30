package com.ll.blog.domain.member.member.service;

import com.ll.blog.Ut.Ut;
import com.ll.blog.domain.member.member.entity.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthTokenService {
    @Value("${custom.jwt}")
    private String jwtSecretKey;

    @Value("${custom.accessToken.expirationSeconds}")
    private long accessTokenExpirationSeconds;

    String genAccessToken(Member member) {
        long id = member.getId();
        String username = member.getUsername();

        return Ut.jwt.toString(
                jwtSecretKey,
                accessTokenExpirationSeconds,
                Map.of("id", id, "username", username)
        );
    }

    Map<String, Object> payload(String accessToken) {
        Map<String, Object> parsedPayload = Ut.jwt.payload(jwtSecretKey, accessToken);

        if (parsedPayload == null) return null;

        long id = (long) (Integer) parsedPayload.get("id");
        String username = (String) parsedPayload.get("username");

        return Map.of("id", id, "username", username);
    }
}