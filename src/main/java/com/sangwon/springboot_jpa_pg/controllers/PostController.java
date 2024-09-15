package com.sangwon.springboot_jpa_pg.controllers;

import com.sangwon.springboot_jpa_pg.entities.Post;
import com.sangwon.springboot_jpa_pg.payloads.PostDto;
import com.sangwon.springboot_jpa_pg.payloads.PostResponse;
import com.sangwon.springboot_jpa_pg.repositories.PostRepository;
import com.sangwon.springboot_jpa_pg.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // register a post
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
}
