package com.npsolutions.cartserver.model;

import com.npsolutions.cartserver.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;
    private double cart_price;

    @OneToMany(mappedBy = "cart")
    private List<ProductDTO> productsList;
}
