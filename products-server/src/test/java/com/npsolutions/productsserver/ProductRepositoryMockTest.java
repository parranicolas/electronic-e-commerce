package com.npsolutions.productsserver;

import com.npsolutions.productsserver.model.Category;
import com.npsolutions.productsserver.model.Product;
import com.npsolutions.productsserver.repository.IProductRepository;
import com.npsolutions.productsserver.service.IProductService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IProductService productService;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Product product01 = Product.builder()
                .prod_name("computer")
                .prod_category(Category.builder().cat_id(1L).build())
                .prod_desc("")
                .prod_stock(Double.parseDouble("10"))
                .prod_price(Double.parseDouble("200000.00"))
                .prod_status("Created")
                .create_at(new Date())
                .prod_brand("LG").build();

        productRepository.save(product01);

        List<Product> founds = productService.findByCategory(product01.getProd_category());
        assertThat(founds.size()).isEqualTo(3);
    }
}
