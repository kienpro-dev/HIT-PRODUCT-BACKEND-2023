package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Integer> {
    @Query("SELECT s FROM Shop s WHERE s.id = ?1")
    Optional<Shop> findById(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Shop s SET s.name = ?1, s.address = ?2, s.hotline = ?3, s.timeOpen = ?4, s.timeClose = ?5, s.lastModifiedDate = CURRENT_TIMESTAMP where s.id = ?6")
    void updateShop(String name, String address, String hotline, Time timeOpen, Time timeClose, int id);
}
