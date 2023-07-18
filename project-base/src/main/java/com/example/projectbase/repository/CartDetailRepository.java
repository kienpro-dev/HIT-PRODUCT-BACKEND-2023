package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Cart;
import com.example.projectbase.domain.entity.CartDetail;
import com.example.projectbase.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    CartDetail findByCartAndProduct(Cart cart, Product product);

    @Transactional
    @Modifying
    @Query("UPDATE CartDetail cp SET cp.quantity = ?1 WHERE cp.cart.id = ?2")
    void updateCartDetail(int quantity, int cartId);
}
