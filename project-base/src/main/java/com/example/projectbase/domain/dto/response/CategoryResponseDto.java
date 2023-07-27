package com.example.projectbase.domain.dto.response;


import com.example.projectbase.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private int categoryId;

    private String name;

    private Collection<Product> products;


}
