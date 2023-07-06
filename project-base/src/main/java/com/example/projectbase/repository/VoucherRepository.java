package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher,Integer> {
}
