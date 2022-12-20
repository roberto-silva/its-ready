package com.roberto.taPronto.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.roberto.taPronto.security.AuthorizationUtil.handleForbiddenError;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTUtil jwtUtil;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        if (request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
        } else {
            try {
                var authorizationHeader = request.getHeader(AUTHORIZATION);
                if (Strings.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
                    var token = authorizationHeader.substring("Bearer ".length());
                    jwtUtil.checkIfTokenIsValid(token);
                    UsernamePasswordAuthenticationToken authenticationToken = jwtUtil.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } else {
                    throw new RuntimeException("Invalid Token");
                }
            } catch (Exception exception) {
                handleForbiddenError(response, exception);
            }
        }
    }

}
