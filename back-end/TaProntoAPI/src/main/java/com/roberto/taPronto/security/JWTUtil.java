package com.roberto.taPronto.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

@Component
public class JWTUtil {

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token.expiration-minutes}")
    private String tokenExpirationInMinutes;

    @Value("${jwt.refresh-token.expiration-minutes}")
    private String refreshTokenExpirationInMinutes;
    private final UserDetailsServiceImpl userDetailsService;

    public JWTUtil(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String username) throws ParseException {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return Jwts.builder().setSubject(username)
                .setExpiration(getTokenExpirationDate(tokenExpirationInMinutes))
                .claim(AUTHORITIES_KEY, user.getAuthorities())
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    public String generateRefreshToken(String username) throws ParseException {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return Jwts.builder().setSubject(username)
                .setExpiration(getTokenExpirationDate(refreshTokenExpirationInMinutes))
                .claim(AUTHORITIES_KEY, user.getAuthorities())
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();
    }

    public boolean checkIfTokenIsValid(String token) {

        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            String userName = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (Objects.nonNull(userName) && Objects.nonNull(expirationDate) && now.before(expirationDate)) {
                return true;
            } else {
                throw new RuntimeException("Invalid Token");
            }
        }
        throw new RuntimeException("Invalid Token");
    }


    public String getUserName(String token) {

        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            return claims.getSubject();
        }
        return null;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (checkIfTokenIsValid(token)) {
            String username = getUserName(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        return null;
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private Date getTokenExpirationDate(String tokenExpirationInMinutes) {
        return new Date(System.currentTimeMillis() + Long.parseLong(tokenExpirationInMinutes) * 60 * 1000);
    }

}
