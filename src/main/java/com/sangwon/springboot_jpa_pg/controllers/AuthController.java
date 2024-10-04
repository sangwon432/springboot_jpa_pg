package com.sangwon.springboot_jpa_pg.controllers;

import com.sangwon.springboot_jpa_pg.payloads.SignUpDto;
import com.sangwon.springboot_jpa_pg.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> signUpUser(@RequestBody SignUpDto signUpDto) {
        String response = authService.signUpUser(signUpDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
