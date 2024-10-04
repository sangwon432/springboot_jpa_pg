package com.sangwon.springboot_jpa_pg.services;

import com.sangwon.springboot_jpa_pg.payloads.LogInDto;
import com.sangwon.springboot_jpa_pg.payloads.SignUpDto;

public interface AuthService {
    String loginUser(LogInDto logInDto);
    String signUpUser(SignUpDto signUpDto);
}
