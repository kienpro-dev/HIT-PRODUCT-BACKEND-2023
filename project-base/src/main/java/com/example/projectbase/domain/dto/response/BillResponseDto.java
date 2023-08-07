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
public class BillResponseDto extends ProductCartDto {
    private int billId;

    private String nameCustomer;

    private String address;

    private String phoneNumber;

    private Date timeShip;

    private double distance;

    private double feeShip;

    public BillResponseDto(int billId, String nameCustomer, String address, String phoneNumber, Date timeShip, double distance, double feeShip, int productId, String shopName, String shopAddress,String productName, String productImageUrl, int quantity, int price) {
        super(productId, shopName, shopAddress, productName, productImageUrl, quantity, price);
        this.billId = billId;
        this.nameCustomer = nameCustomer;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.timeShip = timeShip;
        this.distance = distance;
        this.feeShip = feeShip;
    }
}
