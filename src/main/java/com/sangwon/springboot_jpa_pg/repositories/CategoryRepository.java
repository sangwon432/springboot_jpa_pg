package com.sangwon.springboot_jpa_pg.repositories;

import com.sangwon.springboot_jpa_pg.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
