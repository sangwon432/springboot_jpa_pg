package com.sangwon.springboot_jpa_pg.services.impl;

import com.sangwon.springboot_jpa_pg.entities.User;
import com.sangwon.springboot_jpa_pg.exceptions.UserApiException;
import com.sangwon.springboot_jpa_pg.payloads.LogInDto;
import com.sangwon.springboot_jpa_pg.payloads.SignUpDto;
import com.sangwon.springboot_jpa_pg.repositories.UserRepository;
import com.sangwon.springboot_jpa_pg.services.AuthService;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;


    public AuthServiceImpl(
            UserRepository userRepository
//            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String loginUser(LogInDto logInDto) {
        return "";
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
//        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setPassword(signUpDto.getPassword());

        userRepository.save(user);
        return "User Registered Successfully";
    }
}