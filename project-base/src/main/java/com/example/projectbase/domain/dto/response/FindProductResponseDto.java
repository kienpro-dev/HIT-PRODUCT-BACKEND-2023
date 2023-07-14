package com.example.projectbase.domain.dto.response;

import com.example.projectbase.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindProductResponseDto {
    private String productName;

    private String productImageUrl;

    private int productPrice;

    private int productStock;

    private String categoryName;

    private String shopName;

    private String shopAddress;

    private String shopTimeClose;

    private String shopTimeOpen;

    private String shopHotline;

    public FindProductResponseDto(String productName, String productImageUrl, int productPrice, int productStock, String categoryName, String shopName, String shopAddress, Date shopTimeClose, Date shopTimeOpen, String shopHotline) {
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.categoryName = categoryName;
        this.shopName = shopName;
        this.shopAddress = shopAddress;

        SimpleDateFormat timeFormat = new SimpleDateFormat(CommonConstant.PATTERN_TIME);
        this.shopTimeClose = timeFormat.format(shopTimeClose);
        this.shopTimeOpen = timeFormat.format(shopTimeOpen);
        this.shopHotline = shopHotline;
    }
}
