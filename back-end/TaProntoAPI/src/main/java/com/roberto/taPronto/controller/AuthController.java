package com.roberto.taPronto.controller;

import com.roberto.taPronto.security.JWTUtil;
import com.roberto.taPronto.security.UserSpringSecurity;
import com.roberto.taPronto.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private JWTUtil jwtUtil;

    @PostMapping("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws ParseException {

        UserSpringSecurity userSpringSecurity = UserService.getUserLogged();

        if (Objects.nonNull(userSpringSecurity)) {
            String token = jwtUtil.generateToken(userSpringSecurity.getUsername());
            response.addHeader("Auhorization", "Bearer " + token);
            response.addHeader("access-control-expose-headers", "Authorization");
        }

        return ResponseEntity.noContent().build();
    }
}
