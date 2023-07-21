package com.example.projectbase.repository;

import com.example.projectbase.domain.dto.response.FindProductResponseDto;
import com.example.projectbase.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Optional<Product> findById(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.name = ?1, p.price = ?2, p.description = ?3, p.image = ?4, p.discount = ?5, p.stock = ?6, p.lastModifiedDate = CURRENT_TIMESTAMP where p.id = ?7")
    void updateProduct(String name, int price, String description, String image, float discount, int stock, int id);

    @Query(value = "SELECT new com.example.projectbase.domain.dto.response.FindProductResponseDto (p.id, p.name, p.image, p.price, p.stock,p.description) FROM Product p INNER JOIN p.shops sp  WHERE sp.id= ?1 ")
    Page<FindProductResponseDto> findProductByShop(int id, Pageable pageable);

    @Query(value = "SELECT new com.example.projectbase.domain.dto.response.FindProductResponseDto (p.id, p.name, p.image, p.price, p.stock,p.description) FROM Product p INNER JOIN p.categories sc  WHERE sc.id= ?1 ")
    Page<FindProductResponseDto> findProductByCategory(int id, Pageable pageable);
}
