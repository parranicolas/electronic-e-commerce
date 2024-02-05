package com.npsolutions.productsserver.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prod_id;
    private String prod_name;
    private String prod_brand;
    private String prod_desc;
    private Double prod_price;
    private Double prod_stock;
    private String prod_status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category prod_category;



}
