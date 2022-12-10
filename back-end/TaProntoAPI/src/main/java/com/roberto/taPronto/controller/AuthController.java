package com.roberto.taPronto.controller;

import com.roberto.taPronto.dto.CredentialsDTO;
import com.roberto.taPronto.security.JWTUtil;
import com.roberto.taPronto.security.UserSpringSecurity;
import com.roberto.taPronto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Void> auth(@RequestBody @Validated CredentialsDTO credentialsDTO, HttpServletResponse response) throws ParseException {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                credentialsDTO.getUsername(), credentialsDTO.getPassword(), new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", "Bearer " + jwtUtil.generateToken(authToken.getName()));


        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws ParseException {

        UserSpringSecurity userSpringSecurity = UserService.getUserLogged();

        if (Objects.nonNull(userSpringSecurity)) {
            String token = jwtUtil.generateToken(userSpringSecurity.getUsername());
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Authorization", "Bearer " + token);

        }

        return ResponseEntity.ok().build();
    }
}
