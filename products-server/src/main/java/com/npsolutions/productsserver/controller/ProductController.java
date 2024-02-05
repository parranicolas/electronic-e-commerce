package com.npsolutions.productsserver.controller;

import com.npsolutions.productsserver.model.Product;
import com.npsolutions.productsserver.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productServ;

    @GetMapping("/get-all")
    public List<Product> getProducts (){
        return productServ.getProducts();
    }

    @GetMapping("/get-by-id/{product_id}")
    public Product getProductById(@PathVariable Long product_id){
        return productServ.getProductById(product_id);
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody Product product){
        productServ.createProduct(product);
        return "Producto creado correctamente";
    }

    @DeleteMapping ("/delete/{prod_id}")
    public String deleteProduct(@PathVariable Long product_id){
        productServ.deleteProduct(product_id);
        return "Producto eliminado correctamente";
    }

    @PutMapping("/edit/{product_id}")
    public String editProduct(@PathVariable Long product_id, @RequestBody Product product){
        productServ.editProduct(product_id, product);
        return "Producto editado correctamente";
    }

}
