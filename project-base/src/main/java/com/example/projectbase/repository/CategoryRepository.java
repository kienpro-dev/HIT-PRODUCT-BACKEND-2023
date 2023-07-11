package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT c FROM Category c WHERE c.id = ?1")
    Optional<Category> findById(int id);

    @Transactional
    @Modifying
    @Query("UPDATE Category c SET c.name = ?1, c.lastModifiedDate = CURRENT_TIMESTAMP where c.id = ?2")
    void updateCategory(String name, int id);
}
