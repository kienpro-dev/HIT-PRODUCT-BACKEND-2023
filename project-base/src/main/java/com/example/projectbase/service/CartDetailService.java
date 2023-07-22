package com.example.projectbase.service;

import com.example.projectbase.domain.dto.CartDetailDto;
import com.example.projectbase.domain.dto.response.CartResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;

import java.util.List;

public interface CartDetailService {
    Object addProductToCart(CartDetailDto cartDetailDto);

    List<CartResponseDto> getCartInfo(int cartId);

    CommonResponseDto updateCartInfo(CartDetailDto cartDetailDto);
}
