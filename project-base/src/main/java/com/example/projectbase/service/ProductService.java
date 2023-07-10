package com.example.projectbase.service;

import com.example.projectbase.domain.dto.ProductDto;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.entity.Product;

public interface ProductService {
    Product createProduct(ProductDto productDto);

    Product updateProduct(int id, ProductDto productDto);

    Product getProductById(int id);

    Object deleteProductById(int id);

    PaginationResponseDto<Product> getProducts(PaginationFullRequestDto request);
}
