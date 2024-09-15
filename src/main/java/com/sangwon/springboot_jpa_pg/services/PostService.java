package com.sangwon.springboot_jpa_pg.services;

import com.sangwon.springboot_jpa_pg.payloads.PostDto;
import com.sangwon.springboot_jpa_pg.payloads.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts();
    PostDto getPostById(long id);
    PostDto updatePostById(long id, PostDto postDto);
    void deletePostById(long id);
}
