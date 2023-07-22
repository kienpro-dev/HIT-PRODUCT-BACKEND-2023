package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.SuccessMessage;
import com.example.projectbase.domain.dto.CartDetailDto;
import com.example.projectbase.domain.dto.response.CartResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.Cart;
import com.example.projectbase.domain.entity.CartDetail;
import com.example.projectbase.domain.entity.Product;
import com.example.projectbase.domain.mapper.CartDetailMapper;
import com.example.projectbase.exception.InvalidException;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.CartDetailRepository;
import com.example.projectbase.repository.CartRepository;
import com.example.projectbase.repository.ProductRepository;
import com.example.projectbase.service.CartDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    private final CartDetailMapper cartDetailMapper;

    @Override
    public Object addProductToCart(CartDetailDto cartDetailDto) {
        Optional<Cart> cart = Optional.ofNullable(cartRepository.findById(cartDetailDto.getCartId()).orElseThrow(() -> new NotFoundException(ErrorMessage.Cart.ERR_NOT_FOUND_ID, new String[]{String.valueOf(cartDetailDto.getCartId())})));

        Optional<Product> product = Optional.ofNullable(productRepository.findById(cartDetailDto.getProductId()).orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{String.valueOf(cartDetailDto.getProductId())})));

        CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(cart.get(), product.get());

        if(cartDetail != null) {
            cartDetail.setQuantity(cartDetail.getQuantity() + cartDetailDto.getQuantity());
            cartDetailRepository.updateCartDetail(cartDetail.getQuantity(), cartDetailDto.getCartId(), cartDetailDto.getProductId());
        } else {
            cartDetailRepository.save(cartDetailMapper.toCartDetail(cartDetailDto));
        }

        return null;
    }

    @Override
    public List<CartResponseDto> getCartInfo(int cartId) {
        Optional<Cart> cart = Optional.ofNullable(cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException(ErrorMessage.Cart.ERR_NOT_FOUND_ID, new String[]{String.valueOf(cartId)})));

        return cartDetailRepository.findCartDetail(cartId);
    }

    @Override
    public CommonResponseDto updateCartInfo(CartDetailDto cartDetailDto) {
        Optional<Cart> cart = Optional.ofNullable(cartRepository.findById(cartDetailDto.getCartId()).orElseThrow(() -> new NotFoundException(ErrorMessage.Cart.ERR_NOT_FOUND_ID, new String[]{String.valueOf(cartDetailDto.getCartId())})));

        Optional<Product> product = Optional.ofNullable(productRepository.findById(cartDetailDto.getProductId()).orElseThrow(() -> new NotFoundException(ErrorMessage.Product.ERR_NOT_FOUND_ID, new String[]{String.valueOf(cartDetailDto.getProductId())})));

        CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(cart.get(), product.get());

        if(cartDetailDto.getQuantity() <= 0) {
            cartDetailRepository.delete(cartDetail);

            return new CommonResponseDto(true, SuccessMessage.DELETE_PRODUCT_TO_CART);
        }

        cartDetailRepository.updateCartDetail(cartDetailDto.getQuantity(), cartDetailDto.getCartId(), cartDetailDto.getProductId());

        return new CommonResponseDto(true, SuccessMessage.ADD_PRODUCT_TO_CART);
    }
}
