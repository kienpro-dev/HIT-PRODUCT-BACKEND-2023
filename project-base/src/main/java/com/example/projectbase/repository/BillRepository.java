package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Bill b SET b.customer.id = ?2 WHERE b.id = ?1")
    void addBillForCustomer(int id, int customerId);
}
