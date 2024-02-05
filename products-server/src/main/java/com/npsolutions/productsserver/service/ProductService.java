package com.npsolutions.productsserver.service;

import com.npsolutions.productsserver.model.Product;
import com.npsolutions.productsserver.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

//    Inyectar dependencias de mi repositorio
    @Autowired
    private IProductRepository productRepo;

    @Override
    public List<Product> getProducts() {
        List<Product> productsList = productRepo.findAll();
        return productsList;
    }

    @Override
    public String createProduct(Product product) {
        productRepo.save(product);
        return "Producto creado correctamente.";
    }

    @Override
    public Product getProductById(Long product_id) {
        Product product = (Product) productRepo.findById(product_id).orElse(null);
        return product;
    }

    @Override
    public String deleteProduct(Long product_id) {
        productRepo.deleteById(product_id);
        return "Producto eliminado correctamente";
    }

    @Override
    public String editProduct(Long product_id, Product product) {
        Product prod = this.getProductById(product_id);
        prod = product;
        productRepo.save(prod);

        return "Producto editado correctamente.";
    }
}
