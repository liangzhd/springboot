package org.example.bigevent.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String Key= "secret";

    // 生成token
    public static String getToken(Map<String,Object> claims) {
        return JWT.create()
                .withClaim("claims",claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))
                .sign(Algorithm.HMAC256(Key));
    }

    // 验证token
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(Key))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
