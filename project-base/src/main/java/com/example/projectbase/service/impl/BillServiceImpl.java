package com.example.projectbase.service.impl;

import com.example.projectbase.domain.dto.BillDto;
import com.example.projectbase.domain.entity.Bill;
import com.example.projectbase.domain.entity.Customer;
import com.example.projectbase.domain.mapper.BillMapper;
import com.example.projectbase.repository.BillRepository;
import com.example.projectbase.repository.CustomerRepository;
import com.example.projectbase.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
//    private final BillRepository billRepository;
//
//    private final CustomerRepository customerRepository;
//
//    private final BillMapper billMapper;
//
//    @Override
//    public void createBillForCustomer(int customerId) {
//        Optional<Customer> customer = customerRepository.findById(customerId);
//        Bill bill = new Bill();
//        bill.setCustomer(customer.get());
//        billRepository.save(bill);
//    }
}
