package com.example.projectbase.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_detail_id",insertable = false, updatable = false, nullable = false)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",foreignKey = @ForeignKey(name = "FK_CART_PRODUCT1"),referencedColumnName = "product_id")
    private Product product;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id",foreignKey = @ForeignKey(name = "FK_CART_PRODUCT2"),referencedColumnName = "cart_id")
    private Cart cart;

    @Column(nullable = false)
    private int quantity;
}