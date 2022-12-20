package com.roberto.taPronto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roberto.taPronto.security.JWTUtil;
import com.roberto.taPronto.security.UserSpringSecurity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static com.roberto.taPronto.security.AuthorizationUtil.handleForbiddenError;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@Slf4j
public class AuthController {

    private JWTUtil jwtUtil;

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            var authorizationHeader = request.getHeader(AUTHORIZATION);
            if (Strings.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
                var token = authorizationHeader.substring("Bearer ".length());
                jwtUtil.checkIfTokenIsValid(token);
                UsernamePasswordAuthenticationToken authenticationToken = jwtUtil.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                String username = ((UserSpringSecurity) authenticationToken.getPrincipal()).getUsername();

                String access_token = null;
                String refresh_token = null;

                try {
                    access_token = jwtUtil.generateToken(username);
                    refresh_token = jwtUtil.generateRefreshToken(username);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }
            throw new RuntimeException("Invalid Token");
        } catch (Exception exception) {
            handleForbiddenError(response, exception);
        }
    }
}
