package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Integer> {
}
