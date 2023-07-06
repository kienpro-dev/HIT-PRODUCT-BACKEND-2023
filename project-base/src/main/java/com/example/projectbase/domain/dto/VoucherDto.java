package com.example.projectbase.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Min;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDto {
    private String name;

    private String discount;

    private Date timeStart;

    private Date timeEnd;

    @Min(value = 0)
    private int quantity;

    private int customer_id;
}
