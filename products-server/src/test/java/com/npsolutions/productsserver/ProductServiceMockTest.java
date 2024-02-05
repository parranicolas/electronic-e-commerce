package com.npsolutions.productsserver;

import com.npsolutions.productsserver.model.Category;
import com.npsolutions.productsserver.model.Product;
import com.npsolutions.productsserver.repository.IProductRepository;
import com.npsolutions.productsserver.service.IProductService;
import com.npsolutions.productsserver.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private IProductRepository productRepository;

    private IProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository);
        Product computer = Product.builder()
                .prod_id(1L)
                .prod_name("computer")
                .prod_category(Category.builder().cat_id(1L).build())
                .prod_price(Double.parseDouble("350000.00"))
                .prod_stock(Double.parseDouble("5"))
                .prod_brand("Panasonic")
                .build();
        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);
    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProductById(1L);
        assertThat(found.getProd_name()).isEqualTo("computer");
    }
    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productService.updateStock(1L,Double.parseDouble("8"));
        assertThat(newStock.getProd_stock()).isEqualTo(13);
    }


}
