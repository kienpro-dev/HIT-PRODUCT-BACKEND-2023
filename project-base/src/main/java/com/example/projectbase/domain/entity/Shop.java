package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import lombok.*;

import javax.persistence.*;
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
    private Date timeOpen;

    @Column(nullable = false)
    private Date timeClose;


    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "shops")
    private List<Category> categories;

}
