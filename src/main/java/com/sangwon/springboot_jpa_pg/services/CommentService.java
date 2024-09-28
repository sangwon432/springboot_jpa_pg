package com.sangwon.springboot_jpa_pg.services;

import com.sangwon.springboot_jpa_pg.entities.Comment;
import com.sangwon.springboot_jpa_pg.payloads.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto getCommentByCommentId(Long postId, Long commentId);
    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);
    void deleteComment(Long postId, Long commentId);
}
