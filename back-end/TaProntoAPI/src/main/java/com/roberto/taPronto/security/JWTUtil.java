package com.roberto.taPronto.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private String expirationTime;

    public String generateToken(String username) throws ParseException {
        return Jwts.builder().setSubject(username)
                .setExpiration(getExpirationTime().getTime())
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    private Calendar getExpirationTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MILLISECOND, Integer.parseInt(this.expirationTime));
        cal.getTime();
        return cal;
    }
}
