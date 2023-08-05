package com.example.projectbase.repository;

import com.example.projectbase.domain.dto.response.BillResponseDto;
import com.example.projectbase.domain.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail,Integer> {
    @Query("SELECT new com.example.projectbase.domain.dto.response." +
            "BillResponseDto(b.id, b.nameCustomer, b.address, b.phoneNumber, b.timeShip, b.distance, b.feeShip, p.id, p.name, p.image, bd.quantity, p.price) " +
            "FROM BillDetail bd INNER JOIN bd.bill b INNER JOIN bd.product p INNER JOIN b.customer.cart.cartDetails WHERE b.id = ?1")
    BillResponseDto findBillDetail(int billId);
}
