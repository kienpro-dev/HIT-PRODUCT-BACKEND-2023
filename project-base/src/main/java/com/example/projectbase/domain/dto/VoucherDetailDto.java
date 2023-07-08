package com.example.projectbase.domain.dto;

import com.example.projectbase.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDetailDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private int customer_id;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private int voucher_id;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String status;
}
