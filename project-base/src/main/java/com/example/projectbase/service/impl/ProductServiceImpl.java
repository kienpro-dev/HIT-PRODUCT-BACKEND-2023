package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.SortByDataConstant;
import com.example.projectbase.domain.dto.ProductDto;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.pagination.PaginationSortRequestDto;
import com.example.projectbase.domain.dto.pagination.PagingMeta;
import com.example.projectbase.domain.dto.response.FindProductResponseDto;
import com.example.projectbase.domain.entity.Category;
import com.example.projectbase.domain.entity.Product;
import com.example.projectbase.domain.entity.Shop;
import com.example.projectbase.domain.mapper.ProductMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.CategoryRepository;
import com.example.projectbase.repository.ProductRepository;
import com.example.projectbase.repository.ShopRepository;
import com.example.projectbase.service.ProductService;
import com.example.projectbase.util.PaginationUtil;
import com.example.projectbase.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final UploadFileUtil uploadFileUtil;

    private final ShopRepository shopRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, ProductDto productDto) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{String.valueOf(id)});
        }

        productRepository.updateProduct(productDto.getName(), productDto.getPrice(), productDto.getDescription(), uploadFileUtil.uploadFile(productDto.getImage()), productDto.getDiscount(), productDto.getStock(), id);

        return productRepository.save(product.get());
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{String.valueOf(id)}));
    }

    @Override
    public Object deleteProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{String.valueOf(id)}));
        productRepository.delete(product);
        return null;
    }

    @Override
    public PaginationResponseDto<Product> getProducts(PaginationFullRequestDto request) {
        Pageable pageable = PaginationUtil.buildPageable(request, SortByDataConstant.PRODUCT);

        Page<Product> page = productRepository.findAll(pageable);

        PaginationResponseDto<Product> responseDto = new PaginationResponseDto<>();
        responseDto.setItems(page.getContent());

        PagingMeta pagingMeta = new PagingMeta(page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getSize(), request.getSortBy(), request.getIsAscending().toString());
        responseDto.setMeta(pagingMeta);
        return responseDto;
    }

    @Override
    public PaginationResponseDto<FindProductResponseDto> findProductsByShop(int id, PaginationSortRequestDto request) {
        Optional<Shop> shop = Optional.ofNullable(shopRepository.findById(id).orElseThrow(()->new NotFoundException(ErrorMessage.Shop.ERR_NOT_FOUND_ID,new String[]{String.valueOf(id)})));

        Pageable pageable = PaginationUtil.buildPageable(request, SortByDataConstant.PRODUCT);

        Page<FindProductResponseDto> page = productRepository.findProductByShop(id,pageable);

        PaginationResponseDto<FindProductResponseDto> responseDto = new PaginationResponseDto<>();
        responseDto.setItems(page.getContent());

        PagingMeta pagingMeta = new PagingMeta(page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getSize(), request.getSortBy(), request.getIsAscending().toString());
        responseDto.setMeta(pagingMeta);
        return responseDto;

    }

    @Override
    public PaginationResponseDto<FindProductResponseDto> findProductsByCategory(int id, PaginationSortRequestDto request) {
        Optional<Category> shop = Optional.ofNullable(categoryRepository.findById(id).orElseThrow(()->new NotFoundException(ErrorMessage.Category.ERR_NOT_FOUND_ID,new String[]{String.valueOf(id)})));

        Pageable pageable = PaginationUtil.buildPageable(request, SortByDataConstant.PRODUCT);

        Page<FindProductResponseDto> page = productRepository.findProductByCategory(id,pageable);

        PaginationResponseDto<FindProductResponseDto> responseDto = new PaginationResponseDto<>();
        responseDto.setItems(page.getContent());

        PagingMeta pagingMeta = new PagingMeta(page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getSize(), request.getSortBy(), request.getIsAscending().toString());
        responseDto.setMeta(pagingMeta);
        return responseDto;
    }
}
