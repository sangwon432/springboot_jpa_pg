package com.sangwon.springboot_jpa_pg.services;

import com.sangwon.springboot_jpa_pg.payloads.PostDto;
import com.sangwon.springboot_jpa_pg.payloads.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(String id);
    PostDto updatePostById(String id, PostDto postDto);
    void deletePostById(String  id);
}
