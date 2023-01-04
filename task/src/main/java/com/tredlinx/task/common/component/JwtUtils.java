package com.tredlinx.task.common.component;

import com.tredlinx.task.common.exception.CustomException;
import com.tredlinx.task.common.exception.model.enumurate.ApiExceptionCode;
import com.tredlinx.task.common.jwt.model.dto.JwtTokens;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {
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

    public String getAuthPayload(String token)  {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey(secretKey)).build()
                .parseClaimsJws(token)
                .getBody().get("sub", String.class);
    }
    private Key getSigninKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public void tokenValidationCheck(String token) throws CustomException {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token =  token.substring(7);
        }
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigninKey(secretKey)).build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
            throw new CustomException(ApiExceptionCode.UNAUTHORIZED);
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
            throw new CustomException(ApiExceptionCode.UNAUTHORIZED);
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
            throw new CustomException(ApiExceptionCode.UNAUTHORIZED);
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
            throw new CustomException(ApiExceptionCode.UNAUTHORIZED);
        }
    }
}
