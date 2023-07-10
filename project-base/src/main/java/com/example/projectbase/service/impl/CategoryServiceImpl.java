package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.SortByDataConstant;
import com.example.projectbase.domain.dto.CategoryDto;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.entity.Category;
import com.example.projectbase.domain.mapper.CategoryMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.CategoryRepository;
import com.example.projectbase.service.CategoryService;
import com.example.projectbase.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(int id, CategoryDto categoryDto) {
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isEmpty()) {
            throw new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{String.valueOf(id)});
        }

        categoryRepository.updateCategory(categoryDto.getName(), id);

        return categoryRepository.save(category.get());
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{String.valueOf(id)}));
    }

    @Override
    public Object deleteCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID, new String[]{String.valueOf(id)}));
        categoryRepository.delete(category);
        return null;
    }

    @Override
    public PaginationResponseDto<Category> getCategories(PaginationFullRequestDto request) {
        Pageable pageable = PaginationUtil.buildPageable(request, SortByDataConstant.CATEGORY);

        Page<Category> page = categoryRepository.findAll(pageable);

        PaginationResponseDto<Category> responseDto = new PaginationResponseDto<>();
        responseDto.setItems(page.getContent());

        PagingMeta pagingMeta = new PagingMeta(page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getSize(), request.getSortBy(), request.getIsAscending().toString());
        responseDto.setMeta(pagingMeta);
        return responseDto;
    }
}
