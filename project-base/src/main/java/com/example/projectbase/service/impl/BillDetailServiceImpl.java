package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.domain.dto.BillDetailDto;
import com.example.projectbase.domain.dto.CartDetailDto;
import com.example.projectbase.domain.dto.response.BillResponseDto;
import com.example.projectbase.domain.dto.response.CartResponseDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;
import com.example.projectbase.domain.entity.*;
import com.example.projectbase.domain.mapper.BillDetailMapper;
import com.example.projectbase.domain.mapper.BillMapper;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.BillDetailRepository;
import com.example.projectbase.repository.BillRepository;
import com.example.projectbase.repository.CartDetailRepository;
import com.example.projectbase.repository.CustomerRepository;
import com.example.projectbase.service.BillDetailService;
import com.example.projectbase.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {
    private final CustomerRepository customerRepository;

    private final CartDetailRepository cartDetailRepository;

    private final BillRepository billRepository;

    private final BillDetailRepository billDetailRepository;

    private final BillDetailMapper billDetailMapper;

    @Override
    public BillResponseDto getBillInfo(int customerId) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException(ErrorMessage.Customer.ERR_NOT_FOUND_ID, new String[]{String.valueOf(customerId)})));

        Address addressCustomer = customer.get().getAddress();

        Cart cart = customer.get().getCart();
        List<CartResponseDto> cartResponseDto = cartDetailRepository.findCartDetail(cart.getId());

        Bill bill = new Bill();
        bill.setNameCustomer(customer.get().getFullName());
        bill.setPhoneNumber(customer.get().getPhoneNumber());
        bill.setCustomer(customer.get());
        bill.setAddress(addressCustomer.getAddressName());

        for (CartResponseDto c : cartResponseDto) {
            BillDetailDto billDetailDto = new BillDetailDto(c.getProductId(), bill.getId(), c.getQuantity());
            billDetailRepository.save(billDetailMapper.toBillDetail(billDetailDto));
        }

        billRepository.save(bill);

        return billDetailRepository.findBillDetail(bill.getId());
    }

    @Override
    public CommonResponseDto buy(int billId) {
        return null;
    }
}
