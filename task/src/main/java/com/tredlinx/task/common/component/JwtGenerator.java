package com.tredlinx.task.common.component;

import com.tredlinx.task.common.jwt.model.dto.JwtTokens;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
    @Value("${jwt.accessToken.validTime}")
    private long accessTokenValidTime;
    @Value("${jwt.secret}")
    private String secretKey;

    public JwtTokens createJwtToken(String uid) {
        Claims claims = Jwts.claims().setSubject(uid);
        Date now = new Date();
        String accessToken = "Bearer " +
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(System.currentTimeMillis()+accessTokenValidTime*1000*60))
                        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                        .compact();
        return new JwtTokens(accessToken);
    }

    public static String getUid() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
}
