package com.example.projectbase.service;

import com.example.projectbase.domain.dto.CartDetailDto;
import com.example.projectbase.domain.dto.response.BillResponseDto;
import com.example.projectbase.domain.dto.response.CartResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;

public interface BillDetailService {
    BillResponseDto getBillInfo(int customerId);

    CommonResponseDto buy(int billId);
}
