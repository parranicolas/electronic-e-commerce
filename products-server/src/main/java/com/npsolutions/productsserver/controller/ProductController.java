package com.npsolutions.productsserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.npsolutions.productsserver.model.Category;
import com.npsolutions.productsserver.model.ErrorMessage;
import com.npsolutions.productsserver.model.Product;
import com.npsolutions.productsserver.service.IProductService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productServ;

    @GetMapping("/get-by-category")
    public ResponseEntity<List<Product>> productList(@RequestParam(name = "categoryId",required = false)Long categoryId){
        List<Product> products = new ArrayList<>();
        if(categoryId == null){
            products = productServ.getProducts();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        else{
            products = productServ.findByCategory(Category.builder().cat_id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }
    @GetMapping("/stock/{prod_id}")
    public ResponseEntity<Product> updateStock(@PathVariable(name = "prod_id")Long prod_id, @RequestParam(name="quantity", required = false)Double quantity){
        Product prod = productServ.updateStock(prod_id,quantity);
        if(prod== null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prod);
    }

    @GetMapping("/get-all")
    public List<Product> getProducts (){
        return productServ.getProducts();
    }

    @GetMapping("/get-by-id/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long product_id){
        Product product = productServ.getProductById(product_id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Product prodCreate = productServ.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(prodCreate);
    }

    @DeleteMapping ("/delete/{prod_id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(name = "prod_id") Long prod_id){
        Product prodDelete = productServ.deleteProduct(prod_id);
        if(prodDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prodDelete);
    }

    @PutMapping("/edit/{product_id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long product_id, @RequestBody Product product){
        product.setProd_id(product_id);
        Product prod = productServ.editProduct(product);
        if(prod == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prod);
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .msg_code("01")
                .messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }

}
