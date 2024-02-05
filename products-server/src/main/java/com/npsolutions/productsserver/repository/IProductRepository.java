package com.npsolutions.productsserver.repository;

import com.npsolutions.productsserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository <Product, Long>{
}
