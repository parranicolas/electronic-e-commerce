package com.npsolutions.cartserver.repository;

import com.npsolutions.cartserver.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductAPI {

    @GetMapping("/products/get-all")
    public List<ProductDTO> getProducts();
}
