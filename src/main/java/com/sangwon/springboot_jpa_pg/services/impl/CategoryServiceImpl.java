package com.sangwon.springboot_jpa_pg.services.impl;

import com.sangwon.springboot_jpa_pg.payloads.CategoryDto;
import com.sangwon.springboot_jpa_pg.repositories.CategoryRepository;
import com.sangwon.springboot_jpa_pg.services.CategoryService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto getCategory(String categoryId) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return List.of();
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        return null;
    }

    @Override
    public void deleteCategory(String categoryId) {

    }
}
