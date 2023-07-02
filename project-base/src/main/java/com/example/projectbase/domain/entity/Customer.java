package com.example.projectbase.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, nullable = false)
    private int id;

    @Column(nullable = false)
    private String fullName;

    private String address;

    private Date dob;

    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private User user;
}
