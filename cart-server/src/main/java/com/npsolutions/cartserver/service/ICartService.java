package com.npsolutions.cartserver.service;

import com.npsolutions.cartserver.dto.ProductDTO;
import com.npsolutions.cartserver.model.Cart;

import java.util.List;

public interface ICartService {

    //Guarda el carrito
    public String saveCart(Cart cart);

    //Busca los productos disponibles
    public List<ProductDTO> getProducts();

    //Añade articulos al carrito
    public String addArticleToCart(Long prod_id);

    //Quita un articulo del carrito pasandole el id
    public String removeArticle (Long prod_id);

    //Borra el carrito
    public String deleteCart(Long cart_id);

}
