package com.example.projectbase.service;

import com.example.projectbase.domain.dto.CartDetailDto;
import com.example.projectbase.domain.dto.response.BillResponseDto;
import com.example.projectbase.domain.dto.response.CartResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.BillDetail;

import java.util.List;

public interface BillDetailService {
    List<BillResponseDto> getBillInfo(int customerId);

    CommonResponseDto buy(int billId, int customerId);

    List<BillDetail> getAllBill();
}
