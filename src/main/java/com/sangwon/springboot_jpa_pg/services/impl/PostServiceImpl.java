package com.sangwon.springboot_jpa_pg.services.impl;

import com.sangwon.springboot_jpa_pg.entities.Post;
import com.sangwon.springboot_jpa_pg.exceptions.ResourceNotFoundException;
import com.sangwon.springboot_jpa_pg.payloads.PostDto;
import com.sangwon.springboot_jpa_pg.payloads.PostResponse;
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
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert DTO to Entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        // convert Entity to DTO
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
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
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(long id, PostDto postDto) {
        Post updatedpost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        updatedpost.setTitle(postDto.getTitle());
        updatedpost.setContent(postDto.getContent());
        updatedpost.setCategory(postDto.getCategory());
        updatedpost.setDescription(postDto.getDescription());

        updatedpost = postRepository.save(updatedpost);
        return mapToDto(updatedpost);
    }

    @Override
    public void deletePostById(long id) {
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
