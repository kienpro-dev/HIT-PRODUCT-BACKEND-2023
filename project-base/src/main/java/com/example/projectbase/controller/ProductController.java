package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.ProductDto;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "API get product")
    @GetMapping(UrlConstant.Product.GET_PRODUCT)
    public ResponseEntity<?> getProductById(@PathVariable int productId) {
        return VsResponseUtil.success(productService.getProductById(productId));
    }

    @Operation(summary = "API get all product")
    @GetMapping(UrlConstant.Product.GET_PRODUCTS)
    public ResponseEntity<?> getProducts(@Valid @ParameterObject PaginationFullRequestDto requestDTO) {
        return VsResponseUtil.success(productService.getProducts(requestDTO));
    }

    @Operation(summary = "API update product")
    @PutMapping(UrlConstant.Product.UPDATE_PRODUCT)
    public ResponseEntity<?> updateProduct(@PathVariable int productId, @Valid @RequestBody ProductDto productDto) {
        return VsResponseUtil.success(productService.updateProduct(productId, productDto));
    }

    @Operation(summary = "API delete product")
    @DeleteMapping(UrlConstant.Product.DELETE_PRODUCT)
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        return VsResponseUtil.success(productService.deleteProductById(productId));
    }
}
