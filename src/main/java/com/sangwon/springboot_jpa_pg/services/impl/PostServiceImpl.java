package com.sangwon.springboot_jpa_pg.services.impl;

import com.sangwon.springboot_jpa_pg.entities.Category;
import com.sangwon.springboot_jpa_pg.entities.Post;
import com.sangwon.springboot_jpa_pg.exceptions.ResourceNotFoundException;
import com.sangwon.springboot_jpa_pg.payloads.PostDto;
import com.sangwon.springboot_jpa_pg.payloads.PostResponse;
import com.sangwon.springboot_jpa_pg.repositories.CategoryRepository;
import com.sangwon.springboot_jpa_pg.repositories.PostRepository;
import com.sangwon.springboot_jpa_pg.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(
            PostRepository postRepository,
            ModelMapper modelMapper,
            CategoryRepository categoryRepository
    ) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
//
//        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
//
//        //convert DTO to Entity
//        Post post = mapToEntity(postDto);
//        post.setCategory(category);
//        Post newPost = postRepository.save(post);
//
//        // convert Entity to DTO
//        PostDto postResponse = mapToDto(newPost);
//        return postResponse;
        return null;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<PostDto> contents = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getNumberOfElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        postResponse.setContents(contents);

        return postResponse;
    }

    @Override
    public PostDto getPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(String id, PostDto postDto) {
//        Post updatedpost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
//        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
//        updatedpost.setTitle(postDto.getTitle());
//        updatedpost.setContent(postDto.getContent());
//        updatedpost.setCategory(category);
//        updatedpost.setDescription(postDto.getDescription());
//
//        updatedpost = postRepository.save(updatedpost);
//        return mapToDto(updatedpost);
        return null;
    }

    @Override
    public void deletePostById(String  id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    // convert entity into dto
    private PostDto mapToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    // convert dto to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }
}
