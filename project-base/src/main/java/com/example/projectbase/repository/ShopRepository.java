package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Integer> {
}
