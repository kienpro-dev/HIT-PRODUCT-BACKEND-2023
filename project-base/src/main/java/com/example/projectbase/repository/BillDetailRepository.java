package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetail,Integer> {
}
