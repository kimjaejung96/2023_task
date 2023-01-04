package com.tredlinx.task.common.component;

import com.tredlinx.task.common.jwt.model.dto.JwtTokens;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class JwtTest {
    private String secret = "a2ltamFlanVuZ3Rlc3RzZWNyZXRza2V5MDEwNTA2NDE0MDZraW1qYWVqdW5ndGVzdHNlY3JldHNrZXkwMTA1MDY0MTQwNmtpbWphZWp1bmd0ZXN0c2VjcmV0c2tleTAxMDUwNjQxNDA2a2ltamFlanVuZ3Rlc3RzZWNyZXRza2V5MDEwNTA2NDE0MDY=";
    @Autowired
    JwtGenerator jwtGenerator;

    @Test
    void Jwt_생성_및_claim_확인() {
        String uid = "test word in secret context";
        JwtTokens token = jwtGenerator.createJwtToken(uid);
        String result = getAuthPayload(token.getAccessToken().substring(7));
        assertThat(uid, equalTo(result));
    }


    private String getAuthPayload(String token)  {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey(secret)).build()
                .parseClaimsJws(token)
                .getBody().get("sub", String.class);
    }
    private Key getSigninKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
