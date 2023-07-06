package com.example.projectbase.domain.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private String name;

    @Min(value = 0)
    private int price;

    @Length(max = 100000)
    private String description;

    private String image;

    @Max(value = 100)
    @Min(value = 0)
    private float discount;

    private String stock;


}
