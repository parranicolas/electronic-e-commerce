package com.npsolutions.productsserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotEmpty(message = "El nombre no debe estar vacio")
    private String prod_name;
    private String prod_brand;
    private String prod_desc;
    private Double prod_price;
    @Positive(message = "El valor del stock debe ser mayor que cero")
    private Double prod_stock;
    private String prod_status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_at;

    @NotNull(message = "La categoria debe estar presente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category prod_category;



}
