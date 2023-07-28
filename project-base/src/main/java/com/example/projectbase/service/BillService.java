package com.example.projectbase.service;

import com.example.projectbase.domain.dto.BillDto;

public interface BillService {
    void createBillForCustomer(BillDto billDto);
}
