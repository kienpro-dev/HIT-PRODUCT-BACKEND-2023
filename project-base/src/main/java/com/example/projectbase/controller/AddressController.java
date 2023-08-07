package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.domain.dto.AddressDto;
import com.example.projectbase.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestApiV1
public class AddressController {
    private final AddressService addressService;

    @Operation(summary = "API save location customer")
    @PostMapping(UrlConstant.Address.SAVE_LOCATION_CUSTOMER)
    public ResponseEntity<?> saveLocationCustomer(@Valid @PathVariable int customerId, @RequestBody AddressDto addressDto) {
        return VsResponseUtil.success(addressService.saveLocationCustomer(customerId, addressDto));
    }
}
