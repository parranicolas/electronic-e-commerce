package com.npsolutions.cartserver.service;

import com.npsolutions.cartserver.dto.ProductDTO;
import com.npsolutions.cartserver.model.Cart;
import com.npsolutions.cartserver.repository.ICartRepository;
import com.npsolutions.cartserver.repository.ProductAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService{

    @Autowired
    private ProductAPI prodAPI;

    @Autowired
    private ICartRepository cartRepo;

    @Override
    public String saveCart(Cart cart) {
        cartRepo.save(cart);
        return "Carrito guardado correctamente";
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<ProductDTO> productsList = prodAPI.getProducts();
        return productsList;
    }

    @Override
    public String addArticleToCart(Long prod_id) {


        return null;
    }

    @Override
    public String removeArticle(Long prod_id) {
        return null;
    }

    @Override
    public String deleteCart(Long cart_id) {
        cartRepo.deleteById(cart_id);
        return "Carrito eliminado correctamente";
    }
}
