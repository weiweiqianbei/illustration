package com.illustration.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    // 需要一个至少256位（32字节）的密钥。你可以使用以下代码生成一个强密钥
    private static final String SECRET = "D/Xg1kVQdweiI1vMm4iuD6N2KDd6VDj5rfPhp2/fnu8=";
    private SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    /**
     * 创建token
     * @param claims
     * @return
     */
    public String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .header()
                .add("alg", "HS256")
                .add("typ", "JWT")
                .and()
                .claims(claims)
                .issuedAt(new Date())   // 签发时间
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 过期时间
                .signWith(KEY)
                .compact();
        return token;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public Claims parseToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }

    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 生成一个安全的密钥
        String base64EncodedKey = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(base64EncodedKey);
    }
}
