package com.sangwon.springboot_jpa_pg.repositories;

import com.sangwon.springboot_jpa_pg.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
