package com.example.projectbase.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {
    private int product_id;

    private int cart_id;

    @Min(value = 1)
    private int quantity;
}
