package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.constant.UrlConstant;
import com.example.projectbase.service.BillDetailService;
import com.example.projectbase.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RequiredArgsConstructor
@RestApiV1
public class BillController {
    private final BillDetailService billDetailService;
    private final BillService billService;

    @Operation(summary = "API get all bill")
    @GetMapping(UrlConstant.Bill.BILLS)
    public ResponseEntity<?> getAllBill() {
        return VsResponseUtil.success(billDetailService.getAllBill());
    }

    @Operation(summary = "API cancel order")
    @PutMapping(UrlConstant.Bill.CANCEL_ORDER)
    public ResponseEntity<?> cancelOrder(@PathVariable int billId){
        return VsResponseUtil.success(billService.cancelOrder(billId));
    }
}
