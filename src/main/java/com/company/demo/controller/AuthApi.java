package com.company.demo.controller;

import javax.validation.Valid;

import com.company.demo.domain.entities.Role;
import com.company.demo.domain.entities.User;
import com.company.demo.auth.AuthRequest;
import com.company.demo.auth.AuthResponse;
import com.company.demo.auth.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Trabajeremos con el usuario:
 * {
 *     "email":"nam@codejava.net",
 *     "password":"nam2020"
 * }
 */

@RestController
public class AuthApi {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();

            //UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String accessToken = jwtUtil.generateAccessToken(user);


            List<String> roles = new ArrayList<>();
            for (Role role: user.getRoles()) {
                roles.add(role.getName().toString());
            }
            //System.out.println("Roles: " + roles.toString());

            AuthResponse response = new AuthResponse(user.getEmail(), roles, accessToken);

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, accessToken.toString()).body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}