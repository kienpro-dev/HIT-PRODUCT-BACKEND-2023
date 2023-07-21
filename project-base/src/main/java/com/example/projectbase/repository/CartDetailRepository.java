package com.example.projectbase.repository;

import com.example.projectbase.domain.dto.response.CartResponseDto;
import com.example.projectbase.domain.entity.Cart;
import com.example.projectbase.domain.entity.CartDetail;
import com.example.projectbase.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    CartDetail findByCartAndProduct(Cart cart, Product product);

    List<CartDetail> findAllByCart(Cart cart);

    @Query("SELECT new com.example.projectbase.domain.dto.response.CartResponseDto(c.id, p.id, p.name, p.image, cd.quantity, p.price) FROM CartDetail cd INNER JOIN cd.product p INNER JOIN cd.cart c WHERE c.id = ?1")
    List<CartResponseDto> findCartDetail(int cartId);

    @Transactional
    @Modifying
    @Query("UPDATE CartDetail cp SET cp.quantity = ?1 WHERE cp.cart.id = ?2 and cp.product.id = ?3")
    void updateCartDetail(int quantity, int cartId, int productId);
}
