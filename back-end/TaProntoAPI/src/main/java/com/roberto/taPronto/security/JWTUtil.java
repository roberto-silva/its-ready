package com.roberto.taPronto.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-minutes}")
    private String expirationInMinutes;

    public String generateToken(String username) throws ParseException {

        Date expirationTimeMillisecond = new Date(System.currentTimeMillis() + Long.parseLong(expirationInMinutes) * 60 * 1000);
        return Jwts.builder().setSubject(username)
                .setExpiration(expirationTimeMillisecond)
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    public boolean isValid(String token) {

        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            String userName = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return Objects.nonNull(userName) && Objects.nonNull(expirationDate) && now.before(expirationDate);
        }
        return false;
    }


    public String getUserName(String token) {

        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            return claims.getSubject();
        }
        return null;
    }

    private Claims getClaims(String token) {

        try {
            return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
