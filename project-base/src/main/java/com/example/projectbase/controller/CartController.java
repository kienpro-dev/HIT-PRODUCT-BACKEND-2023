package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.CartDetailDto;
import com.example.projectbase.service.CartDetailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class CartController {
    private final CartDetailService cartDetailService;

    @Operation(summary = "API add product to cart")
    @PostMapping(value = UrlConstant.Cart.ADD_PRODUCT_TO_CART)
    public ResponseEntity<?> addProductToCart(@Valid @PathVariable int cartId, @RequestParam int productId, @RequestParam int quality) {
        return VsResponseUtil.success(cartDetailService.addProductToCart(new CartDetailDto(cartId, productId, quality)));
    }
}
