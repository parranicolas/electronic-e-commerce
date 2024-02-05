package com.npsolutions.productsserver.service;

import com.npsolutions.productsserver.model.Category;
import com.npsolutions.productsserver.model.Product;
import com.npsolutions.productsserver.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductService {

    //    Inyectar dependencias de mi repositorio
    @Autowired
    private IProductRepository productRepo;

    @Override
    public List<Product> findByCategory(Category category) {
        List<Product> productList = productRepo.findAll();
        List<Product> productListByCategory = new ArrayList<>();
        for (Product prod:productList){
            if(prod.getProd_category().equals(category)){
                productListByCategory.add(prod);
            }
        }



        return productListByCategory;
    }
    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public String createProduct(Product product) {
        product.setProd_status("CREATED");
        product.setCreate_at(new Date());
        productRepo.save(product);
        return "Producto creado correctamente.";
    }

    @Override
    public Product getProductById(Long product_id) {
        return productRepo.findById(product_id).orElse(null);
    }

    @Override
    public String deleteProduct(Long product_id) {
        Product prod = getProductById(product_id);
        if(null == prod){
            return null;
        }
        prod.setProd_status("DELETED");
        productRepo.save(prod);

        return "Producto eliminado correctamente";
    }

    @Override
    public String editProduct(Long product_id, Product product) {
        Product prod = getProductById(product_id);
        if(null == prod){
            return null;
        }
        prod.setProd_name(product.getProd_name());
        prod.setProd_desc(product.getProd_desc());
        prod.setProd_brand(product.getProd_brand());
        prod.setProd_price(product.getProd_price());
        prod.setProd_category(product.getProd_category());

        productRepo.save(prod);

        return "Producto editado correctamente.";
    }

    @Override
    public Product updateStock(Long product_id, Double quantity) {
        Product prod = getProductById(product_id);
        if(null == prod){
            return null;
        }
        Double stock = prod.getProd_stock() + quantity;
        prod.setProd_stock(stock);
        return productRepo.save(prod);
    }
}
