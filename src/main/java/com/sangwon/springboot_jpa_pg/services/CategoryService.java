package com.sangwon.springboot_jpa_pg.services;

import com.sangwon.springboot_jpa_pg.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(String categoryId);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(CategoryDto categoryDto, String categoryId);
    void deleteCategory(String categoryId);
}
