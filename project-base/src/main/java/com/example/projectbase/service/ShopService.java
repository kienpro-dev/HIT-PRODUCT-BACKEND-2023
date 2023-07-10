package com.example.projectbase.service;

import com.example.projectbase.domain.dto.ShopDto;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.entity.Shop;

public interface ShopService {
    Shop createShop(ShopDto shopDto);

    Shop updateShop(int id, ShopDto shopDto);

    Shop getShopById(int id);

    Object deleteShopById(int id);

    PaginationResponseDto<Shop> getShops(PaginationFullRequestDto request);
}
