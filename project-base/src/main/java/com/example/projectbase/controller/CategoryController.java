package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.CategoryDto;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "API get category")
    @GetMapping(UrlConstant.Category.GET_CATEGORY)
    public ResponseEntity<?> getCategoryById(@PathVariable int categoryId) {
        return VsResponseUtil.success(categoryService.getCategoryById(categoryId));
    }

    @Operation(summary = "API get all category")
    @GetMapping(UrlConstant.Category.GET_CATEGORIES)
    public ResponseEntity<?> getCategorys(@Valid @ParameterObject PaginationFullRequestDto requestDTO) {
        return VsResponseUtil.success(categoryService.getCategories(requestDTO));
    }

    @Operation(summary = "API update category")
    @PutMapping(UrlConstant.Category.UPDATE_CATEGORY)
    public ResponseEntity<?> updateCategory(@PathVariable int categoryId, @Valid @RequestBody CategoryDto categoryDto) {
        return VsResponseUtil.success(categoryService.updateCategory(categoryId, categoryDto));
    }

    @Operation(summary = "API delete category")
    @DeleteMapping(UrlConstant.Category.DELETE_CATEGORY)
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) {
        return VsResponseUtil.success(categoryService.deleteCategoryById(categoryId));
    }
}
