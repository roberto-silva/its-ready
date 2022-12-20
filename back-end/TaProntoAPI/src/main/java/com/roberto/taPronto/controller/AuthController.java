package com.roberto.taPronto.controller;

import com.roberto.taPronto.dto.CredentialsDTO;
import com.roberto.taPronto.security.JWTUtil;
import com.roberto.taPronto.security.UserSpringSecurity;
import com.roberto.taPronto.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private JWTUtil jwtUtil;
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping
    public ResponseEntity<Void> auth(@RequestBody @Validated CredentialsDTO credentialsDTO, HttpServletResponse response) throws ParseException {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                credentialsDTO.getUsername(), credentialsDTO.getPassword(), new ArrayList<>());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", "Bearer " + jwtUtil.generateToken(authToken.getName()));


        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletRequest request) throws ParseException {

        // From the HttpRequest get the claims
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");

//        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
//        String token = jwtUtil.generateToken(expectedMap, expectedMap.get("sub").toString());
//        return ResponseEntity.ok(new AuthenticationResponse(token));
        return null;
    }
}
