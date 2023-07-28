package com.example.projectbase.service.impl;

import com.example.projectbase.domain.dto.BillDto;
import com.example.projectbase.domain.entity.Bill;
import com.example.projectbase.domain.mapper.BillMapper;
import com.example.projectbase.repository.BillRepository;
import com.example.projectbase.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;

    private final BillMapper billMapper;

    @Override
    public void createBillForCustomer(BillDto billDto) {
        Bill bill = billRepository.save(billMapper.toBill(billDto));
        billRepository.addBillForCustomer(bill.getId(), billDto.getCustomerId());
    }
}
