package com.npsolutions.productsserver.service;

import com.npsolutions.productsserver.model.Category;
import com.npsolutions.productsserver.model.Product;

import java.util.List;

public interface IProductService {

    //Traigo todos los productos en un ArrayList
    public List<Product> getProducts();

    //Creo un producto
    public String createProduct(Product product);

    //Busco un producto por id
    public Product getProductById(Long product_id);

    //Buscar un producto por categoria
    public List<Product> findByCategory(Category category);

    //Elimino un producto, pasando como parametro el id
    public String deleteProduct(Long product_id);

    //Editar un producto, pasando como parametro el id del producto a editar y un objeto de tipo Producto
    public String editProduct(Long product_id, Product product);

    //Actualizar el stock del producto
    public Product updateStock(Long product_id, Double quantity);
}
