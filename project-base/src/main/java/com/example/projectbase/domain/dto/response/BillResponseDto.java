package com.example.projectbase.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {
    private int billId;

    private String nameCustomer;

    private String address;

    private String phoneNumber;

    private Date timeShip;

    private double feeShip;

    private List<CartResponseDto> listBuy;

    public double getTotalBuyPrice() {
        double total = 0;
        for (CartResponseDto c : listBuy) {
            total += c.getPrice() + c.getQuantity();
        }
        return total;
    }

    public double getTotalBillPrice() {
        return getTotalBuyPrice() + getFeeShip();
    }
}
