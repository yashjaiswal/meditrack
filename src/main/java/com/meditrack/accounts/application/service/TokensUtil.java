package com.meditrack.accounts.application.service;

import com.meditrack.accounts.domain.UserAccount;
import com.meditrack.app.ApplicationConstants;
import com.meditrack.app.config.MeditrackAppParameters;
import io.jsonwebtoken.Claims;
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


    private final MeditrackAppParameters meditrackAppParameters;

    public String generateToken(UserAccount userAccount) {

        long now = System.currentTimeMillis();
        long expiry = now + 86400000; // 24h

        return Jwts
                .builder()
                .setSubject(userAccount.getUserName())
                .claim(ApplicationConstants.USER_ID, userAccount.getUserId())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expiry))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(meditrackAppParameters.getAccountsKey().getBytes());
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
