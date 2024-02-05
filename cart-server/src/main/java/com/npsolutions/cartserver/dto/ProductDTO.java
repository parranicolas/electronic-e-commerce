package com.npsolutions.cartserver.dto;

import com.npsolutions.cartserver.model.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prod_id;
    private String prod_name;
    private double prod_price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


}
