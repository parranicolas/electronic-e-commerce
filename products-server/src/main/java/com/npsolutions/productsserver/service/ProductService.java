package com.npsolutions.productsserver.service;

import com.npsolutions.productsserver.model.Category;
import com.npsolutions.productsserver.model.Product;
import com.npsolutions.productsserver.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    //    Inyectar dependencias de mi repositorio


    private final IProductRepository productRepo;

    //Busca un producto por el id de una categoria dada
    @Override
    public List<Product> findByCategory(Category category) {
        List<Product> productList = productRepo.findAll();
        List<Product> productListByCategory = new ArrayList<>();
        for (Product prod:productList){
            if(prod.getProd_category().getCat_id().equals(category.getCat_id())){
                productListByCategory.add(prod);
            }
        }

        return productListByCategory;
    }
//    Trae todos los productos en una collection
    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

//    Crear un nuevo producto
    @Override
    public Product createProduct(Product product) {
        product.setProd_status("CREATED");
        product.setCreate_at(new Date());
        return productRepo.save(product);

    }

    @Override
    public Product getProductById(Long product_id) {
        return productRepo.findById(product_id).orElse(null);
    }

//    Eliminar un producto cambiando su estado, no eliminandolo de la DB
    @Override
    public Product deleteProduct(Long product_id) {
        Product prod = getProductById(product_id);
        if(null == prod){
            return null;
        }
        prod.setProd_status("DELETED");


        return productRepo.save(prod);
    }

//    Se edita un producto pasando un id, y un producto nuevo
    @Override
    public Product editProduct(Product product) {
        Product prod = getProductById(product.getProd_id());
        if(null == prod){
            return null;
        }
        prod.setProd_name(product.getProd_name());
        prod.setProd_desc(product.getProd_desc());
        prod.setProd_brand(product.getProd_brand());
        prod.setProd_price(product.getProd_price());
        prod.setProd_category(product.getProd_category());



        return productRepo.save(prod);
    }

    //Se actualiza el stock dando un id del producto a modificar
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
