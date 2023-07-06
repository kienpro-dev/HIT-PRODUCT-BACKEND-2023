package com.example.projectbase.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {

    private String name;

    private String address;


    private String hotline;

    private Date timeOpen;

    private Date timeClose;


}
