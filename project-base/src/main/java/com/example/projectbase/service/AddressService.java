package com.example.projectbase.service;

import com.example.projectbase.domain.dto.AddressDto;
import com.example.projectbase.domain.dto.response.CommonResponseDto;

public interface AddressService {
    CommonResponseDto saveLocationCustomer(int customerId, AddressDto addressDto);
}
