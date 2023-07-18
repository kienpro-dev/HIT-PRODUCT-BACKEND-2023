package com.example.projectbase.service;

import com.example.projectbase.domain.dto.CartDetailDto;

public interface CartDetailService {
    Object addProductToCart(CartDetailDto cartDetailDto);
}
