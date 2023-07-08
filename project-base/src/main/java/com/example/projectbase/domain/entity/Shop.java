package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shops")
public class Shop extends DateAuditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id",insertable = false, updatable = false, nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;


    @Column(nullable = false)
    private String hotline;

    @Column(nullable = false)
    private Time timeOpen;

    @Column(nullable = false)
    private Time timeClose;


    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "shops")
    @JsonIgnore
    private List<Category> categories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "shop_product",
            joinColumns = @JoinColumn(name = "shop_id",foreignKey = @ForeignKey(name = "FK_SHOP_PRODUCT1")),
            inverseJoinColumns = @JoinColumn(name = "product_id",foreignKey = @ForeignKey(name = "FK_SHOP_PRODUCT2")))
    @JsonIgnore
    private List<Product> products;

}
