package com.sangwon.springboot_jpa_pg.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LogInDto {
    private String usernameOrEmail;
    private String password;
}
