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
    private int customerId;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private int voucherId;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String status;
}
