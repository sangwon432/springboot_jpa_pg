package com.sangwon.springboot_jpa_pg.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotEmpty(message = "name should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Body should not be empty")
    @Size(min = 10, message = "comment body should be minimum of 10 characters")
    private String body;
}
