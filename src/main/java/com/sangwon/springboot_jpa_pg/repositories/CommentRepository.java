package com.sangwon.springboot_jpa_pg.repositories;

import com.sangwon.springboot_jpa_pg.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByPostId(Long postId);
}
