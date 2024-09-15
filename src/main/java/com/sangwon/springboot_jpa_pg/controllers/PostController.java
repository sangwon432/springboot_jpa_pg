package com.sangwon.springboot_jpa_pg.controllers;

import com.sangwon.springboot_jpa_pg.entities.Post;
import com.sangwon.springboot_jpa_pg.payloads.PostResponse;
import com.sangwon.springboot_jpa_pg.repositories.PostRepository;
import com.sangwon.springboot_jpa_pg.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // get all posts
    @GetMapping("/all")
    public PostResponse getAllPosts() {
        return postService.getAllPosts();
    }
}
