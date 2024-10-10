package com.sangwon.springboot_jpa_pg.services.impl;

import com.sangwon.springboot_jpa_pg.entities.User;
import com.sangwon.springboot_jpa_pg.exceptions.UserApiException;
import com.sangwon.springboot_jpa_pg.payloads.LogInDto;
import com.sangwon.springboot_jpa_pg.payloads.SignUpDto;
import com.sangwon.springboot_jpa_pg.repositories.UserRepository;
import com.sangwon.springboot_jpa_pg.security.JwtTokenProvider;
import com.sangwon.springboot_jpa_pg.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String loginUser(LogInDto logInDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    logInDto.getEmail(), logInDto.getPassword()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);

            System.out.println("generated token: " + token); // print log

            return token;
        } catch (Exception e) {
            System.err.println("Login Fail" + e.getMessage());
            throw new RuntimeException("Log in failed", e);
        }

    }

    @Override
    public String signUpUser(SignUpDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists.");
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists.");
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        userRepository.save(user);
        return "User Registered Successfully";
    }
}