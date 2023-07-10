package com.example.projectbase.service;

import com.example.projectbase.domain.dto.CategoryDto;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.entity.Category;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);

    Category updateCategory(int id, CategoryDto categoryDto);

    Category getCategoryById(int id);

    Object deleteCategoryById(int id);

    PaginationResponseDto<Category> getCategories(PaginationFullRequestDto request);
}
