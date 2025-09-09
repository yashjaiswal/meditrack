package com.meditrack.accounts.application.service;

import com.meditrack.accounts.domain.UserAccount;
import com.meditrack.app.MeditrackAppConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokensUtil {


    private final MeditrackAppConfig meditrackAppConfig;

    public String generateToken(UserAccount userAccount) {
        final Key key = Keys.hmacShaKeyFor(meditrackAppConfig.getAccountsKey().getBytes());
        long now = System.currentTimeMillis();
        long expiry = now + 86400000; // 24h

        return Jwts
                .builder()
                .setSubject(userAccount.getUserName())
                .claim("userId", userAccount.getUserId())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expiry))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

}
